package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.procedimentocirurgiao.IProcedimentoCirurgiaoRepository;
import boletimdasaude.application.usecases.procedimentocirurgiao.ProcedimentoCirurgiaoInteractor;
import boletimdasaude.infra.gateways.cirurgiao.CirurgiaoRepository;
import boletimdasaude.infra.gateways.procedimentocirurgiao.ProcedimentoCirurgiaoRepository;
import boletimdasaude.infra.gateways.procedimentocirurgiao.mappers.ProcedimentoCirurgiaoEntityMapper;
import boletimdasaude.infra.persitence.cirurgiao.ICirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcedimentoCirurgiaoConfig {

    @Bean
    ProcedimentoCirurgiaoInteractor procedimentoCirurgiaoInteractor(IProcedimentoCirurgiaoRepository procedimentoCirurgiaoRepository) {
        return new ProcedimentoCirurgiaoInteractor(procedimentoCirurgiaoRepository);
    }

    @Bean
    ProcedimentoCirurgiaoEntity procedimentoCirurgiaoEntity() {
        return new ProcedimentoCirurgiaoEntity();
    }

    @Bean
    ProcedimentoCirurgiaoRepository procedimentoCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa,
                                                                    CirurgiaoRepository cirurgiaoRepository,
                                                                    ICirurgiaoRepositoryJpa cirurgiaoRepositoryJpa) {
        return new ProcedimentoCirurgiaoRepository(procedimentoCirurgiaoRepositoryJpa, cirurgiaoRepository, cirurgiaoRepositoryJpa);
    }

    @Bean
    ProcedimentoCirurgiaoEntityMapper procedimentoCirurgiaoMapper() {
        return new ProcedimentoCirurgiaoEntityMapper();
    }

}
