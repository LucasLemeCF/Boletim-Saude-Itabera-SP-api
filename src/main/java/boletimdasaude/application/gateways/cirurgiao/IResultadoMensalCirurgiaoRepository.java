package boletimdasaude.application.gateways.cirurgiao;

import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.responses.tabela.TabelaCirurgioesResponse;
import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;

import java.util.Date;
import java.util.List;

public interface IResultadoMensalCirurgiaoRepository {

    ResultadoMensalCirurgiao salvarDadosIniciaisDoMes(ResultadoMensalCirurgiao resultadoMensalCirurgiao, Long procedimentoId);

    ResultadoMensalCirurgiao salvarDadosDoDia(ResultadoDiarioCirurgiao resultadoDiarioCirurgiao, Long cirurgiaoId, Date data);

    boolean existeMesProcedimentoCirurgiao(Date data, Long cirurgiaoId);

    boolean existeDiaCirurgiao(Date data, Long resultadoMensalId);

    ResultadoDiarioCirurgiao atualizarDadosDoDia(Date data, LinhaTabelaRequest linhaTabelaRequest);

    List<TabelaCirurgioesResponse> buscarDadosCirurgioes(String data);

}
