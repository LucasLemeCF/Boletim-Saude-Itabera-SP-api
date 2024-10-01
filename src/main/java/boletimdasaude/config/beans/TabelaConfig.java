package boletimdasaude.config.beans;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.especialidade.IEspecialidadeRepository;
import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.application.usecases.ordemtabela.EditarOrdemTabelaInteractor;
import boletimdasaude.application.usecases.tabela.DadosCirurgiaoInteractor;
import boletimdasaude.application.usecases.tabela.DadosEspecialidadeInteractor;
import boletimdasaude.application.usecases.tabela.MontarOrdemTabela;
import boletimdasaude.application.usecases.tabela.TabelaInteractor;
import boletimdasaude.infra.gateways.especialidade.EspecialidadeRepository;
import boletimdasaude.infra.gateways.especialidade.ResultadoMensalEspecialidadeRepository;
import boletimdasaude.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoMensalEspecialidadeMapper;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IResultadoMensalEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.IResultadoDiarioEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TabelaConfig {

    @Bean
    TabelaInteractor tabelaInteractor(DadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor,
                                      DadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor,
                                      MontarOrdemTabela montarOrdemTabela) {
        return new TabelaInteractor(salvarDadosEspecialidadeInteractor, salvarDadosCirurgiaoInteractor, montarOrdemTabela);
    }

    @Bean
    DadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor(
                ITabelaEspecialidadeRepository tabelaEspecialidadeRepository,
                IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository,
                IOrdemTabelaRepository ordemTabelaRepository
    ) {
        return new DadosEspecialidadeInteractor(tabelaEspecialidadeRepository, resultadoMensalEspecialidadeRepository, ordemTabelaRepository);
    }

    @Primary
    @Bean
    ITabelaEspecialidadeRepository iTabelaEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepository, EspecialidadeEntityMapper especialidadeEntityMapper, ResultadoMensalEspecialidadeMapper resultadoMensalEspecialidadeMapper) {
        return new EspecialidadeRepository(especialidadeRepository, especialidadeEntityMapper, resultadoMensalEspecialidadeMapper);
    }

    @Bean
    ResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepositoryJpa,
                                                                                  IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa,
                                                                                  IResultadoDiarioEspecialidadeRepositoryJpa resultadoDiarioEspecialidadeRepositoryJpa,
                                                                                  IOrdemTabelaRepository tabelaRepository,
                                                                                  IEspecialidadeRepository especialidadeRepository) {
        return new ResultadoMensalEspecialidadeRepository(especialidadeRepositoryJpa, resultadoMensalEspecialidadeRepositoryJpa, resultadoDiarioEspecialidadeRepositoryJpa, tabelaRepository, especialidadeRepository);
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

    @Bean
    MontarOrdemTabela montarOrdemTabela(EditarOrdemTabelaInteractor editarTabelaInteractor) {
        return new MontarOrdemTabela(editarTabelaInteractor);
    }

}
