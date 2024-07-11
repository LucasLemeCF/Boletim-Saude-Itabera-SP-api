package boletimdasaude.config;

import boletimdasaude.application.gateways.especialidade.IEspecialidadeRepository;
import boletimdasaude.application.usecases.especialidade.EspecialidadeInteractor;
import boletimdasaude.infra.controller.especialidade.EspecialidadeMapper;
import boletimdasaude.infra.controller.tabela.mappers.EditarTabelaMapper;
import boletimdasaude.infra.gateways.especialidade.EspecialidadeRepository;
import boletimdasaude.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoMensalEspecialidadeMapper;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;
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
    IEspecialidadeRepository especialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepository, EspecialidadeEntityMapper especialidadeEntityMapper, ResultadoMensalEspecialidadeMapper resultadoMensalEspecialidadeMapper) {
        return new EspecialidadeRepository(especialidadeRepository, especialidadeEntityMapper, resultadoMensalEspecialidadeMapper);
    }

    @Bean
    EspecialidadeEntityMapper especialidadeEntityMapper() {
        return new EspecialidadeEntityMapper();
    }

    @Bean
    ResultadoMensalEspecialidadeMapper resultadoMensalEspecialidadeMapper() {
        return new ResultadoMensalEspecialidadeMapper();
    }

    @Bean
    EspecialidadeMapper especialidadeMapper() {
        return new EspecialidadeMapper();
    }

    @Bean
    EditarTabelaMapper editarTabelaMapper() {
        return new EditarTabelaMapper();
    }

}
