package boletimdasaude.infra.gateways.ordemtabela;


import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.gateways.ordemtabela.mappers.OrdemTabelaEntityMapper;
import boletimdasaude.infra.persitence.ordemtabela.ICabecalhoTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.ILinhaTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.IOrdemTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.ITextoCabecalhoTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.entities.CabecalhoTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.LinhaTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.OrdemTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.TextoCabecalhoTabelaEntity;

import java.util.List;

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
    public OrdemTabela editarOrdemTabela(OrdemTabela ordemTabela) {
        deletarTabelaAnterior();

        OrdemTabelaEntity entity = ordemTabelaEntityMapper.toEntity(ordemTabela);
        entity = repository.saveAndFlush(entity);

        salvarLinhaTabela(entity);
        salvarCabecalhoTabela(entity);

        return OrdemTabelaEntityMapper.toDomain(entity);
    }

    private void deletarTabelaAnterior() {
        linhaTabelaRepository.deleteAll();
        textoCabecalhoTabelaRepository.deleteAll();
        cabecalhoTabelaRepository.deleteAll();
        repository.deleteAll();
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
    public List<OrdemTabela> buscarOrdemTabela() {
        List<OrdemTabelaEntity> ordemTabelaEntities = repository.findAll();
        return OrdemTabelaEntityMapper.toDomainList(ordemTabelaEntities);
    }

}