package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.application.usecases.tabela.SalvarDadosEspecialidadeInteractor;
import boletimdasaude.application.usecases.tabela.TabelaInteractor;
import boletimdasaude.infra.gateways.especialidade.EspecialidadeRepository;
import boletimdasaude.infra.gateways.especialidade.ResultadoMensalEspecialidadeRepository;
import boletimdasaude.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IResultadoMensalEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TabelaConfig {

    @Bean
    TabelaInteractor tabelaInteractor(SalvarDadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor) {
        return new TabelaInteractor(salvarDadosEspecialidadeInteractor);
    }

    @Bean
    SalvarDadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor(
                ITabelaEspecialidadeRepository tabelaEspecialidadeRepository,
                IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository
    ) {
        return new SalvarDadosEspecialidadeInteractor(tabelaEspecialidadeRepository, resultadoMensalEspecialidadeRepository);
    }

    @Bean
    ResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepositoryJpa, IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa) {
        return new ResultadoMensalEspecialidadeRepository(especialidadeRepositoryJpa, resultadoMensalEspecialidadeRepositoryJpa);
    }

    @Bean
    ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity() {
        return new ResultadoDiarioEspecialidadeEntity();
    }

}
