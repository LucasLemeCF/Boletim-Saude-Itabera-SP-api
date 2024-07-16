package boletimdasaude.application.requests.ordemtabela;

import java.util.List;

public record EditarOrdemTabelaRequest(
        List<LinhaOrdemTabelaRequest> linhas,
        List<CabecalhoOrdemTabelaRequest> cabecalhos
) {
}
