package boletimdasaude.application.requests.tabela;

import boletimdasaude.domain.enums.TipoLinha;

public record LinhaTabelaRequest(
        TipoLinha tipo,
        Long componenteId,
        Long posicao,
        int pacientesAtendidos
) {
}
