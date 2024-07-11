package boletimdasaude.infra.gateways.tabela;


import boletimdasaude.application.gateways.tabela.ITabelaRepository;
import boletimdasaude.domain.tabela.OrdemTabela;
import boletimdasaude.infra.gateways.tabela.mappers.OrdemTabelaEntityMapper;
import boletimdasaude.infra.persitence.tabela.IItemTabelaCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.tabela.IItemTabelaEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.tabela.ITabelaRepositoryJpa;
import boletimdasaude.infra.persitence.tabela.entities.ItemTabelaCirurgiaoEntity;
import boletimdasaude.infra.persitence.tabela.entities.ItemTabelaEspecialidadeEntity;
import boletimdasaude.infra.persitence.tabela.entities.OrdemTabelaEntity;

import java.util.List;

public class TabelaRepository implements ITabelaRepository {

    private final ITabelaRepositoryJpa repository;
    private final IItemTabelaEspecialidadeRepositoryJpa itemEspecialidadeRepository;
    private final IItemTabelaCirurgiaoRepositoryJpa itemCirurgiaoRepository;
    private final OrdemTabelaEntityMapper ordemTabelaEntityMapper;

    public TabelaRepository(ITabelaRepositoryJpa repository, IItemTabelaEspecialidadeRepositoryJpa itemEspecialidadeRepository, IItemTabelaCirurgiaoRepositoryJpa itemCirurgiaoRepository,OrdemTabelaEntityMapper ordemTabelaEntityMapper) {
        this.repository = repository;
        this.itemEspecialidadeRepository = itemEspecialidadeRepository;
        this.itemCirurgiaoRepository = itemCirurgiaoRepository;
        this.ordemTabelaEntityMapper = ordemTabelaEntityMapper;
    }

    @Override
    public OrdemTabela editarTabela(OrdemTabela ordemTabela) {
        deletarTabelaAnterior();

        OrdemTabelaEntity entity = ordemTabelaEntityMapper.toEntity(ordemTabela);
        entity = repository.saveAndFlush(entity);

        salvarItemTabelaEspecialidade(entity);
        salvarItemTabelaCirurgiao(entity);

        return OrdemTabelaEntityMapper.toDomain(entity);
    }

    private void deletarTabelaAnterior() {
        itemEspecialidadeRepository.deleteAll();
        itemCirurgiaoRepository.deleteAll();
        repository.deleteAll();
    }

    private void salvarItemTabelaEspecialidade(OrdemTabelaEntity entity) {
        List<ItemTabelaEspecialidadeEntity> listaItemTabelaEspecialidadeEntity = entity.getItemTabelaEspecialidade();

        listaItemTabelaEspecialidadeEntity.forEach(item -> {
            item.setOrdemTabela(entity);
        });

        itemEspecialidadeRepository.saveAllAndFlush(listaItemTabelaEspecialidadeEntity);
    }

    private void salvarItemTabelaCirurgiao(OrdemTabelaEntity entity) {
        List<ItemTabelaCirurgiaoEntity> listaItemTabelaCirurgiaoEntity = entity.getItemTabelaCirurgiao();

        listaItemTabelaCirurgiaoEntity.forEach(item -> {
            item.setOrdemTabela(entity);
        });

        itemCirurgiaoRepository.saveAllAndFlush(listaItemTabelaCirurgiaoEntity);
    }

}
