package boletimdasaude.domain.ordemtabela;

import boletimdasaude.domain.enums.TipoLinha;

public record LinhaTabela(
        Long id,
        Long posicao,
        TipoLinha tipo,
        Long componenteId
) {
}
