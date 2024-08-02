package boletimdasaude.infra.gateways.ordemtabela;


import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoCirurgioesResponse;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoEspecialidadesResponse;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.gateways.ordemtabela.mappers.OrdemTabelaEntityMapper;
import boletimdasaude.infra.gateways.ordemtabela.mappers.TextoCabecalhoTabelaEntityMapper;
import boletimdasaude.infra.persitence.ordemtabela.ICabecalhoTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.ILinhaTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.IOrdemTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.ITextoCabecalhoTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.entities.CabecalhoTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.LinhaTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.OrdemTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.TextoCabecalhoTabelaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdemTabelaRepository implements IOrdemTabelaRepository {

    private final IOrdemTabelaRepositoryJpa repository;
    private final ILinhaTabelaRepositoryJpa linhaTabelaRepository;
    private final ICabecalhoTabelaRepositoryJpa cabecalhoTabelaRepository;
    private final ITextoCabecalhoTabelaRepositoryJpa textoCabecalhoTabelaRepository;
    private final OrdemTabelaEntityMapper ordemTabelaEntityMapper;

    public OrdemTabelaRepository(IOrdemTabelaRepositoryJpa repository, ILinhaTabelaRepositoryJpa itemEspecialidadeRepository,
                                 ICabecalhoTabelaRepositoryJpa itemCirurgiaoRepository, ITextoCabecalhoTabelaRepositoryJpa textoCabecalhoTabelaRepository,
                                 OrdemTabelaEntityMapper ordemTabelaEntityMapper) {
        this.repository = repository;
        this.linhaTabelaRepository = itemEspecialidadeRepository;
        this.cabecalhoTabelaRepository = itemCirurgiaoRepository;
        this.textoCabecalhoTabelaRepository = textoCabecalhoTabelaRepository;
        this.ordemTabelaEntityMapper = ordemTabelaEntityMapper;
    }

    @Override
    public OrdemTabela adicionarOrdemTabela(OrdemTabela ordemTabela) {
        OrdemTabelaEntity entityAntiga = repository.findByData(ordemTabela.data());

        if (entityAntiga != null) {
            return ordemTabela;
        } else {
            OrdemTabelaEntity entity = ordemTabelaEntityMapper.toEntity(ordemTabela);
            entity = repository.saveAndFlush(entity);

            salvarLinhaTabela(entity);
            salvarCabecalhoTabela(entity);

            return OrdemTabelaEntityMapper.toDomain(entity);
        }
    }

    @Override
    public OrdemTabela editarOrdemTabela(OrdemTabela ordemTabela) {
        OrdemTabelaEntity entityAntiga = repository.findByData(ordemTabela.data());

        if (entityAntiga != null) {
            return ordemTabela;
        } else {
            desativaOrdensAnteriores();

            OrdemTabelaEntity entity = ordemTabelaEntityMapper.toEntity(ordemTabela);
            entity = repository.saveAndFlush(entity);

            salvarLinhaTabela(entity);
            salvarCabecalhoTabela(entity);

            return OrdemTabelaEntityMapper.toDomain(entity);
        }
    }

    private void desativaOrdensAnteriores() {
        List<OrdemTabelaEntity> todasOrdens = repository.findAll();

        for (OrdemTabelaEntity ordem : todasOrdens) {
            if (ordem.isAtivo()) {
                ordem.setAtivo(false);
                repository.saveAndFlush(ordem);
            }
        }
    }

    private void salvarLinhaTabela(OrdemTabelaEntity entity) {
        List<LinhaTabelaEntity> listaItemTabelaEspecialidadeEntity = entity.getLinhasTabelaEntity();

        listaItemTabelaEspecialidadeEntity.forEach(item -> item.setOrdemTabela(entity));

        linhaTabelaRepository.saveAllAndFlush(listaItemTabelaEspecialidadeEntity);
    }

    private void salvarCabecalhoTabela(OrdemTabelaEntity entity) {
        List<CabecalhoTabelaEntity> listaCabecalhoTabelaEntity = entity.getCabecalhosTabelaEntity();

        listaCabecalhoTabelaEntity.forEach(item -> {
            item.setOrdemTabela(entity);
            salvarTextoCabecalhoTabela(item);
        });

        cabecalhoTabelaRepository.saveAllAndFlush(listaCabecalhoTabelaEntity);
    }

    private void salvarTextoCabecalhoTabela(CabecalhoTabelaEntity entity) {
        List<TextoCabecalhoTabelaEntity> listaTextoCabecalhoTabelaEntity = entity.getTextos();

        listaTextoCabecalhoTabelaEntity.forEach(texto -> texto.setCabecalhoOrdemTabela(entity));

        textoCabecalhoTabelaRepository.saveAllAndFlush(entity.getTextos());
    }

    @Override
    public OrdemTabela buscarOrdemTabela(String data) {
        Optional<OrdemTabelaEntity> ordemTabelaEntity = buscarOrdemTabelaEntity(data);
        return ordemTabelaEntity.map(OrdemTabelaEntityMapper::toDomain).orElse(null);
    }

    @Override
    public List<TabelaCabecalhoEspecialidadesResponse> buscarCabecalhosEspecialidades(String data) {
        List<TabelaCabecalhoEspecialidadesResponse> cabecalhos = new ArrayList<>();

        Optional<OrdemTabelaEntity> ordemTabelaEntity = buscarOrdemTabelaEntity(data);

        for (CabecalhoTabelaEntity cabecalho : ordemTabelaEntity.get().getCabecalhosTabelaEntity()) {
            if (cabecalho.getTipo().equals(TipoLinha.ESPECIALIDADE_CABECALHO)) {
                cabecalhos.add(new TabelaCabecalhoEspecialidadesResponse(
                    cabecalho.getPosicao(),
                    TextoCabecalhoTabelaEntityMapper.toDomainList(cabecalho.getTextos()),
                    new ArrayList<>()
                ));
            }
        }

        return cabecalhos;
    }

    private Optional<OrdemTabelaEntity> buscarOrdemTabelaEntity(String data) {
        Optional<OrdemTabelaEntity> ordemTabelaEntity = Optional.ofNullable(repository.findByData(data));

        if (ordemTabelaEntity.isEmpty()) {
            List<OrdemTabelaEntity> ordemTabelaEntities = repository.findAll();
            for (OrdemTabelaEntity ordemTabela : ordemTabelaEntities) {
                if (ordemTabela.isAtivo()) {
                    return Optional.of(ordemTabela);
                }
            }
        }

        return ordemTabelaEntity;
    }

    @Override
    public List<TabelaCabecalhoCirurgioesResponse> buscarCabecalhosCirurgioes(String data) {
        List<TabelaCabecalhoCirurgioesResponse> cabecalhos = new ArrayList<>();

        Optional<OrdemTabelaEntity> ordemTabelaEntity = buscarOrdemTabelaEntity(data);

        for (CabecalhoTabelaEntity cabecalho : ordemTabelaEntity.get().getCabecalhosTabelaEntity()) {
            if (cabecalho.getTipo().equals(TipoLinha.CIRURGIAO_CABECALHO)) {
                cabecalhos.add(new TabelaCabecalhoCirurgioesResponse(
                        cabecalho.getPosicao(),
                        TextoCabecalhoTabelaEntityMapper.toDomainList(cabecalho.getTextos()),
                        new ArrayList<>()
                ));
            }
        }

        return cabecalhos;
    }

}
