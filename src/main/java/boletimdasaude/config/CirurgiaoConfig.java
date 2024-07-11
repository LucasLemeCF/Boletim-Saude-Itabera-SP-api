package boletimdasaude.config;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.application.usecases.cirurgiao.CirurgiaoInteractor;
import boletimdasaude.infra.gateways.cirurgiao.CirurgiaoRepository;
import boletimdasaude.infra.gateways.cirurgiao.mappers.CirurgiaoEntityMapper;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.persitence.cirurgiao.ICirurgiaoRepositoryJpa;
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
    ICirurgiaoRepository cirurgiaoRepository(ICirurgiaoRepositoryJpa cirurgiaoRepository, CirurgiaoEntityMapper cirurgiaoEntityMapper, ResultadoMensalCirurgiaoMapper resultadoMensalCirurgiaoMapper) {
        return new CirurgiaoRepository(cirurgiaoRepository, cirurgiaoEntityMapper, resultadoMensalCirurgiaoMapper);
    }

    @Bean
    CirurgiaoEntityMapper cirurgiaoEntityMapper() {
        return new CirurgiaoEntityMapper();
    }

    @Bean
    ResultadoMensalCirurgiaoMapper resultadoMensalCirurgiaoMapper() {
        return new ResultadoMensalCirurgiaoMapper();
    }

}
