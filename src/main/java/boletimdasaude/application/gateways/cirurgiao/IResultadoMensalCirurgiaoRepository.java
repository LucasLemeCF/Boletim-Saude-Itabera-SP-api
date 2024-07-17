package boletimdasaude.application.gateways.cirurgiao;

import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;

import java.util.Date;

public interface IResultadoMensalCirurgiaoRepository {

    ResultadoMensalCirurgiao salvarDadosIniciaisDoMes(ResultadoMensalCirurgiao resultadoMensalCirurgiao, Long procedimentoId);

    ResultadoMensalCirurgiao salvarDadosDoDia(ResultadoDiarioCirurgiao resultadoDiarioCirurgiao, Long cirurgiaoId);

    ResultadoMensalCirurgiaoEntity buscarMesAnoProcedimentoCirurgiao(Date data, Long cirurgiaoId);

}
