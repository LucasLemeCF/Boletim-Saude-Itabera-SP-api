package boletimdasaude.domain.tabela;

import java.util.List;

public record OrdemTabela(
        List<ItemTabelaEspecialidade> itensTabelaEspecialidade,
        List<ItemTabelaCirurgiao> itensTabelaCirurgiao
) {
}
