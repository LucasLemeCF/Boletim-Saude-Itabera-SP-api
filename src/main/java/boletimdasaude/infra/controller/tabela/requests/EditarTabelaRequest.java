package boletimdasaude.infra.controller.tabela.requests;

import java.util.List;

public record EditarTabelaRequest(
        List<EspecialidadeTabelaRequest> especialidades,
        List<CirurgiaoTabelaRequest> cirurgioes
) {
}
