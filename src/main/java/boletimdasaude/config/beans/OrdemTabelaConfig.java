package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.usecases.ordemtabela.EditarOrdemTabelaInteractor;
import boletimdasaude.application.usecases.ordemtabela.MontarOrdemTabelaInteractor;
import boletimdasaude.infra.gateways.ordemtabela.LinhaTabelaRepository;
import boletimdasaude.infra.gateways.ordemtabela.OrdemTabelaRepository;
import boletimdasaude.infra.gateways.ordemtabela.mappers.CabecalhoTabelaEntityMapper;
import boletimdasaude.infra.gateways.ordemtabela.mappers.LinhaTabelaEntityMapper;
import boletimdasaude.infra.gateways.ordemtabela.mappers.OrdemTabelaEntityMapper;
import boletimdasaude.infra.persitence.ordemtabela.*;
import boletimdasaude.infra.persitence.ordemtabela.entities.OrdemTabelaEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdemTabelaConfig {

    @Bean
    EditarOrdemTabelaInteractor atualizarTabela(IOrdemTabelaRepository tabelaRepository, MontarOrdemTabelaInteractor montarOrdemTabelaInteractor) {
        return new EditarOrdemTabelaInteractor(tabelaRepository, montarOrdemTabelaInteractor);
    }

    @Bean
    OrdemTabelaEntity ordemTabelaEntity() {
        return new OrdemTabelaEntity();
    }

    @Bean
    IOrdemTabelaRepository tabelaRepository(IOrdemTabelaRepositoryJpa repository, ILinhaTabelaRepositoryJpa itemEspecialidadeRepository,
                                            ICabecalhoTabelaRepositoryJpa itemCirurgiaoRepository,
                                            ITextoCabecalhoTabelaRepositoryJpa textoICabecalhoTabelaRepository,
                                            OrdemTabelaEntityMapper ordemTabelaEntityMapper,
                                            IDataOrdemTabelaRepositoryJpa dataOrdemTabelaRepository) {
        return new OrdemTabelaRepository(repository, itemEspecialidadeRepository, itemCirurgiaoRepository, textoICabecalhoTabelaRepository, ordemTabelaEntityMapper,dataOrdemTabelaRepository);
    }

    @Bean
    OrdemTabelaEntityMapper ordemTabelaEntityMapper() {
        return new OrdemTabelaEntityMapper();
    }

    @Bean
    CabecalhoTabelaEntityMapper itemTabelaCirurgiaoEntityMapper() {
        return new CabecalhoTabelaEntityMapper();
    }

    @Bean
    LinhaTabelaEntityMapper itemTabelaEspecialidadeEntityMapper() {
        return new LinhaTabelaEntityMapper();
    }

    @Bean
    LinhaTabelaRepository linhaTabelaRepository(ILinhaTabelaRepositoryJpa itemEspecialidadeRepository) {
        return new LinhaTabelaRepository(itemEspecialidadeRepository);
    }

    @Bean
    MontarOrdemTabelaInteractor montarOrdemTabelaInteractor(IOrdemTabelaRepository tabelaRepository,
                                                            IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository,
                                                            IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository,
                                                            ICirurgiaoRepository cirurgiaoRepository) {
        return new MontarOrdemTabelaInteractor(tabelaRepository, resultadoMensalEspecialidadeRepository, resultadoMensalCirurgiaoRepository, cirurgiaoRepository);
    }

}
