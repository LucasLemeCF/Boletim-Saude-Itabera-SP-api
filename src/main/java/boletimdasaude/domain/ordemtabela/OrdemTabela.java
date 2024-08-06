package boletimdasaude.domain.ordemtabela;

import java.util.List;

public record OrdemTabela(
        Long id,
        List<DataOrdemTabela> datas,
        boolean ativo,
        List<LinhaTabela> linhasTabela,
        List<CabecalhoTabela> cabecalhosTabela
) {
}
