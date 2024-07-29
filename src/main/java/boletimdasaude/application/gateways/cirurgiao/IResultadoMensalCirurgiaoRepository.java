package boletimdasaude.application.gateways.cirurgiao;

import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.responses.tabela.TabelaCirurgioesResponse;
import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;

import java.util.Date;
import java.util.List;

public interface IResultadoMensalCirurgiaoRepository {

    ResultadoMensalCirurgiao salvarDadosIniciaisDoMes(ResultadoMensalCirurgiao resultadoMensalCirurgiao, Long procedimentoId);

    ResultadoMensalCirurgiao salvarDadosDoDia(ResultadoDiarioCirurgiao resultadoDiarioCirurgiao, Long cirurgiaoId, String data);

    boolean existeMesProcedimentoCirurgiao(String data, Long cirurgiaoId);

    boolean existeDiaCirurgiao(String data, Long resultadoMensalId);

    ResultadoDiarioCirurgiao atualizarDadosDoDia(String data, LinhaTabelaRequest linhaTabelaRequest);

    List<TabelaCirurgioesResponse> buscarDadosCirurgioes(String data);

}
