package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.application.usecases.tabela.DadosCirurgiaoInteractor;
import boletimdasaude.application.usecases.tabela.DadosEspecialidadeInteractor;
import boletimdasaude.application.usecases.tabela.TabelaInteractor;
import boletimdasaude.infra.gateways.ordemtabela.LinhaTabelaRepository;
import boletimdasaude.infra.gateways.especialidade.ResultadoMensalEspecialidadeRepository;
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
                IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository,
                IOrdemTabelaRepository ordemTabelaRepository
    ) {
        return new DadosEspecialidadeInteractor(tabelaEspecialidadeRepository, resultadoMensalEspecialidadeRepository, ordemTabelaRepository);
    }

    @Bean
    ResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepositoryJpa,
                                                                                  IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa,
                                                                                  IResultadoDiarioEspecialidadeRepositoryJpa resultadoDiarioEspecialidadeRepositoryJpa,
                                                                                  LinhaTabelaRepository linhaTabelaRepository) {
        return new ResultadoMensalEspecialidadeRepository(especialidadeRepositoryJpa, resultadoMensalEspecialidadeRepositoryJpa, resultadoDiarioEspecialidadeRepositoryJpa, linhaTabelaRepository);
    }

    @Bean
    ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity() {
        return new ResultadoDiarioEspecialidadeEntity();
    }

    @Bean
    DadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor(ITabelaCirurgiaoRepository tabelaCirurgiaoRepository,
                                                            IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository,
                                                            IOrdemTabelaRepository ordemTabelaRepository) {
        return new DadosCirurgiaoInteractor(tabelaCirurgiaoRepository, resultadoMensalCirurgiaoRepository, ordemTabelaRepository);
    }

}
