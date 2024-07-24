package boletimdasaude.domain.ordemtabela;

import java.util.List;

public record OrdemTabela(
        Long id,
        String data,
        boolean ativo,
        List<LinhaTabela> linhasTabela,
        List<CabecalhoTabela> cabecalhosTabela
) {
}
