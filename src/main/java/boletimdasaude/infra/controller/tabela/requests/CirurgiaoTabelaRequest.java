package boletimdasaude.infra.controller.tabela.requests;

import boletimdasaude.domain.enums.TipoLinha;

public record CirurgiaoTabelaRequest(
        Long posicao,
        TipoLinha tipo,
        Long procedimentoId
) {
}
