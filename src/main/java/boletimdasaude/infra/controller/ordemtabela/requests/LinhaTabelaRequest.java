package boletimdasaude.infra.controller.ordemtabela.requests;

import boletimdasaude.domain.enums.TipoLinha;

public record LinhaTabelaRequest(
        Long posicao,
        TipoLinha tipo,
        Long componenteId
) {
}
