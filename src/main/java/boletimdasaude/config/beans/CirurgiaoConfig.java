package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.usecases.cirurgiao.CirurgiaoInteractor;
import boletimdasaude.infra.gateways.cirurgiao.CirurgiaoRepository;
import boletimdasaude.infra.gateways.cirurgiao.ResultadoMensalCirurgiaoRepository;
import boletimdasaude.infra.gateways.cirurgiao.mappers.CirurgiaoEntityMapper;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.gateways.ordemtabela.LinhaTabelaRepository;
import boletimdasaude.infra.persitence.cirurgiao.ICirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IResultadoDiarioCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IResultadoMensalCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.CirurgiaoEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CirurgiaoConfig {

    @Bean
    CirurgiaoInteractor criarCirurgiao(ICirurgiaoRepository cirurgiaoRepository) {
        return new CirurgiaoInteractor(cirurgiaoRepository);
    }

    @Bean
    CirurgiaoEntity cirurgiaoEntity() {
        return new CirurgiaoEntity();
    }

    @Bean
    ICirurgiaoRepository cirurgiaoRepository(ICirurgiaoRepositoryJpa cirurgiaoRepository, CirurgiaoEntityMapper cirurgiaoEntityMapper ) {
        return new CirurgiaoRepository(cirurgiaoRepository, cirurgiaoEntityMapper);
    }

    @Bean
    CirurgiaoEntityMapper cirurgiaoEntityMapper() {
        return new CirurgiaoEntityMapper();
    }

    @Bean
    ResultadoMensalCirurgiaoMapper resultadoMensalCirurgiaoMapper() {
        return new ResultadoMensalCirurgiaoMapper();
    }

    @Bean
    IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa,
                                                                           IResultadoMensalCirurgiaoRepositoryJpa resultadoMensalCirurgiaoRepositoryJpa,
                                                                           IResultadoDiarioCirurgiaoRepositoryJpa resultadoDiarioCirurgiaoRepositoryJpa,
                                                                           LinhaTabelaRepository linhaTabelaRepository) {
        return new ResultadoMensalCirurgiaoRepository(procedimentoCirurgiaoRepositoryJpa, resultadoMensalCirurgiaoRepositoryJpa, resultadoDiarioCirurgiaoRepositoryJpa, linhaTabelaRepository);
    }

}
