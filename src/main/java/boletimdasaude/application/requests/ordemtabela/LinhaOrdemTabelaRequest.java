package boletimdasaude.application.requests.ordemtabela;

import boletimdasaude.domain.enums.TipoLinha;

public record LinhaOrdemTabelaRequest(
        Long posicao,
        TipoLinha tipo,
        Long componenteId
) {
}
