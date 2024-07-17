package boletimdasaude.config.beans;

import boletimdasaude.infra.gateways.cirurgiao.ProcedimentoCirurgiaoRepository;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ProcedimentoCirurgiaoMapper;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import org.springframework.context.annotation.Bean;

public class ProcedimentoCirurgiaoConfig {

    @Bean
    ProcedimentoCirurgiaoRepository procedimentoCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa) {
        return new ProcedimentoCirurgiaoRepository(procedimentoCirurgiaoRepositoryJpa);
    }

    @Bean
    ProcedimentoCirurgiaoMapper procedimentoCirurgiaoMapper() {
        return new ProcedimentoCirurgiaoMapper();
    }

}
