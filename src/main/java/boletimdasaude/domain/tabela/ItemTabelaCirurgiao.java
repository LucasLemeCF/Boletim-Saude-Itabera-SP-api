package boletimdasaude.domain.tabela;

import boletimdasaude.domain.enums.TipoLinha;

public record ItemTabelaCirurgiao(
        Long id,
        Long posicao,
        TipoLinha tipo,
        Long procedimentoId
) {
}
