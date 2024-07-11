package boletimdasaude.config;

import boletimdasaude.application.gateways.tabela.ITabelaRepository;
import boletimdasaude.application.usecases.tabela.EditarTabelaInteractor;
import boletimdasaude.infra.gateways.tabela.TabelaRepository;
import boletimdasaude.infra.gateways.tabela.mappers.ItemTabelaCirurgiaoEntityMapper;
import boletimdasaude.infra.gateways.tabela.mappers.ItemTabelaEspecialidadeEntityMapper;
import boletimdasaude.infra.gateways.tabela.mappers.OrdemTabelaEntityMapper;
import boletimdasaude.infra.persitence.tabela.IItemTabelaCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.tabela.IItemTabelaEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.tabela.ITabelaRepositoryJpa;
import boletimdasaude.infra.persitence.tabela.entities.OrdemTabelaEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdemTabelaConfig {

    @Bean
    EditarTabelaInteractor atualizarTabela(ITabelaRepository tabelaRepository) {
        return new EditarTabelaInteractor(tabelaRepository);
    }

    @Bean
    OrdemTabelaEntity ordemTabelaEntity() {
        return new OrdemTabelaEntity();
    }

    @Bean
    ITabelaRepository tabelaRepository(ITabelaRepositoryJpa tabelaRepositoryJpa, IItemTabelaEspecialidadeRepositoryJpa itemEspecialidadeRepository, IItemTabelaCirurgiaoRepositoryJpa itemCirurgiaoRepository, OrdemTabelaEntityMapper ordemTabelaEntityMapper) {
        return new TabelaRepository(tabelaRepositoryJpa, itemEspecialidadeRepository, itemCirurgiaoRepository, ordemTabelaEntityMapper);
    }

    @Bean
    OrdemTabelaEntityMapper ordemTabelaEntityMapper() {
        return new OrdemTabelaEntityMapper();
    }

    @Bean
    ItemTabelaCirurgiaoEntityMapper itemTabelaCirurgiaoEntityMapper() {
        return new ItemTabelaCirurgiaoEntityMapper();
    }

    @Bean
    ItemTabelaEspecialidadeEntityMapper itemTabelaEspecialidadeEntityMapper() {
        return new ItemTabelaEspecialidadeEntityMapper();
    }

}
