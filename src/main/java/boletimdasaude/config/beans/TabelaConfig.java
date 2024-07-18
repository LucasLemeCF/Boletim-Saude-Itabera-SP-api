package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.application.usecases.tabela.DadosCirurgiaoInteractor;
import boletimdasaude.application.usecases.tabela.DadosEspecialidadeInteractor;
import boletimdasaude.application.usecases.tabela.TabelaInteractor;
import boletimdasaude.infra.gateways.cirurgiao.ProcedimentoCirurgiaoRepository;
import boletimdasaude.infra.gateways.especialidade.ResultadoMensalEspecialidadeRepository;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IResultadoMensalEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.IResultadoDiarioEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TabelaConfig {

    @Bean
    TabelaInteractor tabelaInteractor(DadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor, DadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor) {
        return new TabelaInteractor(salvarDadosEspecialidadeInteractor, salvarDadosCirurgiaoInteractor);
    }

    @Bean
    DadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor(
                ITabelaEspecialidadeRepository tabelaEspecialidadeRepository,
                IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository
    ) {
        return new DadosEspecialidadeInteractor(tabelaEspecialidadeRepository, resultadoMensalEspecialidadeRepository);
    }

    @Bean
    ResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepositoryJpa,
                                                                                  IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa,
                                                                                  IResultadoDiarioEspecialidadeRepositoryJpa resultadoDiarioEspecialidadeRepositoryJpa) {
        return new ResultadoMensalEspecialidadeRepository(especialidadeRepositoryJpa, resultadoMensalEspecialidadeRepositoryJpa, resultadoDiarioEspecialidadeRepositoryJpa);
    }

    @Bean
    ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity() {
        return new ResultadoDiarioEspecialidadeEntity();
    }

    @Bean
    DadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor(ITabelaCirurgiaoRepository tabelaCirurgiaoRepository,
                                                            IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository) {
        return new DadosCirurgiaoInteractor(tabelaCirurgiaoRepository, resultadoMensalCirurgiaoRepository);
    }

    @Bean
    ITabelaCirurgiaoRepository tabelaCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa) {
        return new ProcedimentoCirurgiaoRepository(procedimentoCirurgiaoRepositoryJpa);
    }

}
