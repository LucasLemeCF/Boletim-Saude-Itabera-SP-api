package boletimdasaude.application.requests.tabela;

import java.util.Date;
import java.util.List;

public record TabelaRequest(
        Date data,
        List<LinhaTabelaRequest> linhas
) {
}
