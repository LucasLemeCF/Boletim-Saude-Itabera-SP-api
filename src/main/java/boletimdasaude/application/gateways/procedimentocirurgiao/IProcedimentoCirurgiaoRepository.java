package boletimdasaude.application.gateways.procedimentocirurgiao;

import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;

import java.util.List;
import java.util.Optional;

public interface IProcedimentoCirurgiaoRepository {

    ProcedimentoCirurgiao criarProcedimentoCirurgiao(ProcedimentoCirurgiao cirurgiao, Long cirurgiaoId);

    List<ProcedimentoCirurgiao> buscarTodosProcedimentosCirurgioes();

    Optional<ProcedimentoCirurgiaoEntity> buscarProcedimentoCirurgiaoEntity(Long procedimentoId);

    ProcedimentoCirurgiao editarProcedimentoCirurgiao(Long id, ProcedimentoCirurgiao cirurgiao);

    String excluirProcedimentoCirurgiao(Long id);

}
