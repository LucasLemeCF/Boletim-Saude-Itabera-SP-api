package boletimdasaude.application.gateways.procedimentocirurgiao;

import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;

import java.util.List;

public interface IProcedimentoCirurgiaoRepository {

    ProcedimentoCirurgiao criarProcedimentoCirurgiao(ProcedimentoCirurgiao cirurgiao, Long cirurgiaoId);

    List<ProcedimentoCirurgiao> buscarTodosProcedimentosCirurgioes();

    ProcedimentoCirurgiao editarProcedimentoCirurgiao(Long id, ProcedimentoCirurgiao cirurgiao);

    String excluirProcedimentoCirurgiao(Long id);

}
