package boletimdasaude.application.gateways.especialidade;

import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;

import java.util.Date;

public interface IResultadoMensalEspecialidadeRepository {

    ResultadoMensalEspecialidade salvarDadosIniciaisDoMes(ResultadoMensalEspecialidade resultadoMensalEspecialidade, Long especialidadeId);

    ResultadoMensalEspecialidade salvarDadosDoDia(ResultadoDiarioEspecialidade resultadoDiarioEspecialidade, Long especialidadeId);

    boolean existeMesAnoEspecialidade(Date data, Long especialidadeId);

    boolean existeDiaEspecialidade(Date data, Long resultadoMensalId);

    ResultadoDiarioEspecialidade atualizarDadosDoDia(Date data, LinhaTabelaRequest linhaTabelaRequest);

}
