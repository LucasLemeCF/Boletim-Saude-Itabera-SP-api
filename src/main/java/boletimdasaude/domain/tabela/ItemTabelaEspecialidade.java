package boletimdasaude.domain.tabela;

import boletimdasaude.domain.enums.TipoLinha;

public record ItemTabelaEspecialidade(
        Long id,
        Long posicao,
        TipoLinha tipo,
        Long especialidadeId
) {
}
