package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.application.usecases.tabela.SalvarDadosCirurgiaoInteractor;
import boletimdasaude.application.usecases.tabela.SalvarDadosEspecialidadeInteractor;
import boletimdasaude.application.usecases.tabela.TabelaInteractor;
import boletimdasaude.infra.gateways.cirurgiao.ProcedimentoCirurgiaoRepository;
import boletimdasaude.infra.gateways.especialidade.ResultadoMensalEspecialidadeRepository;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IResultadoMensalEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TabelaConfig {

    @Bean
    TabelaInteractor tabelaInteractor(SalvarDadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor, SalvarDadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor) {
        return new TabelaInteractor(salvarDadosEspecialidadeInteractor, salvarDadosCirurgiaoInteractor);
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

    @Bean
    SalvarDadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor( ITabelaCirurgiaoRepository tabelaCirurgiaoRepository,
                                                                   IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository) {
        return new SalvarDadosCirurgiaoInteractor(tabelaCirurgiaoRepository, resultadoMensalCirurgiaoRepository);
    }

    @Bean ITabelaCirurgiaoRepository tabelaCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa) {
        return new ProcedimentoCirurgiaoRepository(procedimentoCirurgiaoRepositoryJpa);
    }

}
