package boletimdasaude.application.gateways.especialidade;

import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoEspecialidadesResponse;
import boletimdasaude.application.responses.tabela.TabelaEspecialidadesResponse;
import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;

import java.util.Date;
import java.util.List;

public interface IResultadoMensalEspecialidadeRepository {

    ResultadoMensalEspecialidade salvarDadosIniciaisDoMes(ResultadoMensalEspecialidade resultadoMensalEspecialidade, Long especialidadeId);

    ResultadoMensalEspecialidade salvarDadosDoDia(ResultadoDiarioEspecialidade resultadoDiarioEspecialidade, Long especialidadeId, String data);

    boolean existeMesAnoEspecialidade(String data, Long especialidadeId);

    boolean existeDiaEspecialidade(String data, Long resultadoMensalId);

    ResultadoDiarioEspecialidade atualizarDadosDoDia(String data, LinhaTabelaRequest linhaTabelaRequest);

    List<TabelaEspecialidadesResponse> buscarDadosEspecialidades(String data);

}
