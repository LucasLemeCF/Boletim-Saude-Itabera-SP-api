package boletimdasaude.infra.gateways.ordemtabela;


import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoCirurgioesResponse;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoEspecialidadesResponse;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.gateways.ordemtabela.mappers.OrdemTabelaEntityMapper;
import boletimdasaude.infra.gateways.ordemtabela.mappers.TextoCabecalhoTabelaEntityMapper;
import boletimdasaude.infra.persitence.ordemtabela.*;
import boletimdasaude.infra.persitence.ordemtabela.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdemTabelaRepository implements IOrdemTabelaRepository {

    private final IOrdemTabelaRepositoryJpa repository;
    private final ILinhaTabelaRepositoryJpa linhaTabelaRepository;
    private final ICabecalhoTabelaRepositoryJpa cabecalhoTabelaRepository;
    private final ITextoCabecalhoTabelaRepositoryJpa textoCabecalhoTabelaRepository;
    private final OrdemTabelaEntityMapper ordemTabelaEntityMapper;
    private final IDataOrdemTabelaRepositoryJpa dataOrdemTabelaRepository;

    public OrdemTabelaRepository(IOrdemTabelaRepositoryJpa repository, ILinhaTabelaRepositoryJpa itemEspecialidadeRepository,
                                 ICabecalhoTabelaRepositoryJpa itemCirurgiaoRepository, ITextoCabecalhoTabelaRepositoryJpa textoCabecalhoTabelaRepository,
                                 OrdemTabelaEntityMapper ordemTabelaEntityMapper,
                                 IDataOrdemTabelaRepositoryJpa dataOrdemTabelaRepository) {
        this.repository = repository;
        this.linhaTabelaRepository = itemEspecialidadeRepository;
        this.cabecalhoTabelaRepository = itemCirurgiaoRepository;
        this.textoCabecalhoTabelaRepository = textoCabecalhoTabelaRepository;
        this.ordemTabelaEntityMapper = ordemTabelaEntityMapper;
        this.dataOrdemTabelaRepository = dataOrdemTabelaRepository;
    }

    @Override
    public OrdemTabela adicionarOrdemTabela(OrdemTabela ordemTabela) {
        String dataAtual = ordemTabela.datas().get(0).data();
        DataOrdemTabelaEntity dataEntity = dataOrdemTabelaRepository.findAll().stream()
                .filter(data -> data.getData().equals(dataAtual))
                .findFirst()
                .orElse(null);

        if (dataEntity == null) {
            DataOrdemTabelaEntity dataAtualEntity = new DataOrdemTabelaEntity(dataAtual);
            OrdemTabelaEntity entity = repository.findByAtivo(true);
            dataAtualEntity.setOrdemTabela(entity);
            dataOrdemTabelaRepository.saveAndFlush(dataAtualEntity);
        }

        return ordemTabela;
    }

    @Override
    public OrdemTabela editarOrdemTabela(OrdemTabela ordemTabela) {
        desativaOrdensAnteriores();

        OrdemTabelaEntity entity = ordemTabelaEntityMapper.toEntity(ordemTabela);
        entity = repository.saveAndFlush(entity);

        salvarDatasOrdemTabela(entity);
        salvarLinhaTabela(entity);
        salvarCabecalhoTabela(entity);

        return OrdemTabelaEntityMapper.toDomain(entity);
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

    private void salvarDatasOrdemTabela(OrdemTabelaEntity entity) {
        List<DataOrdemTabelaEntity> datas = entity.getDatas();

        datas.forEach(data -> data.setOrdemTabela(entity));

        dataOrdemTabelaRepository.saveAllAndFlush(datas);
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
        OrdemTabelaEntity ordemTabelaEntity = buscarOrdemTabelaEntity(data);

        if (ordemTabelaEntity == null) {
            return null;
        }

        return OrdemTabelaEntityMapper.toDomain(ordemTabelaEntity);
    }

    @Override
    public List<TabelaCabecalhoEspecialidadesResponse> buscarCabecalhosEspecialidades(String data) {
        List<TabelaCabecalhoEspecialidadesResponse> cabecalhos = new ArrayList<>();

       OrdemTabelaEntity ordemTabelaEntity = buscarOrdemTabelaEntity(data);

       if (ordemTabelaEntity == null) {
           return cabecalhos;
       }

        for (CabecalhoTabelaEntity cabecalho : ordemTabelaEntity.getCabecalhosTabelaEntity()) {
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

    private OrdemTabelaEntity buscarOrdemTabelaEntity(String dataAtual) {
        DataOrdemTabelaEntity dataEntity = dataOrdemTabelaRepository.findAll().stream()
                .filter(data -> data.getData().equals(dataAtual))
                .findFirst()
                .orElse(null);

        if (dataEntity == null) {
            List<OrdemTabelaEntity> ordemTabelaEntities = repository.findAll();
            for (OrdemTabelaEntity ordemTabela : ordemTabelaEntities) {
                if (ordemTabela.isAtivo()) {
                    return ordemTabela;
                }
            }
        } else {
            return dataEntity.getOrdemTabela();
        }

       return null;
    }

    @Override
    public List<TabelaCabecalhoCirurgioesResponse> buscarCabecalhosCirurgioes(String data) {
        List<TabelaCabecalhoCirurgioesResponse> cabecalhos = new ArrayList<>();

        OrdemTabelaEntity ordemTabelaEntity = buscarOrdemTabelaEntity(data);

        if (ordemTabelaEntity == null) {
            return cabecalhos;
        }

        for (CabecalhoTabelaEntity cabecalho : ordemTabelaEntity.getCabecalhosTabelaEntity()) {
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
