package boletimdasaude.application.gateways.tabela;

import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;

import java.util.Optional;

public interface ITabelaCirurgiaoRepository {

    Optional<ProcedimentoCirurgiao> buscarProcedimentoCirurgiao(Long cirurgiaoId);

}
