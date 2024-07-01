package boletimMedico.config;

import boletimMedico.application.gateways.especialidade.IEspecialidadeRepository;
import boletimMedico.application.useCases.especialidade.EspecialidadeInteractor;
import boletimMedico.infra.controller.especialidade.EspecialidadeMapper;
import boletimMedico.infra.gateways.especialidade.EspecialidadeRepository;
import boletimMedico.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;
import boletimMedico.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimMedico.infra.persitence.especialidade.entities.EspecialidadeEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EspecialidadeConfig {

    @Bean
    EspecialidadeInteractor criarEspecialidade(IEspecialidadeRepository especialidadeRepository) {
        return new EspecialidadeInteractor(especialidadeRepository);
    }

    @Bean
    EspecialidadeEntity especialidadeEntity() {
        return new EspecialidadeEntity();
    }

    @Bean
    IEspecialidadeRepository especialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepository, EspecialidadeEntityMapper mapper) {
        return new EspecialidadeRepository(especialidadeRepository, mapper);
    }

    @Bean
    EspecialidadeEntityMapper especialidadeEntityMapper() {
        return new EspecialidadeEntityMapper();
    }

    @Bean
    EspecialidadeMapper especialidadeMapper() {
        return new EspecialidadeMapper();
    }

}
