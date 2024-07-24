package boletimdasaude.application.requests.ordemtabela;

import java.util.List;

public record EditarOrdemTabelaRequest(
        String data,
        List<LinhaOrdemTabelaRequest> linhas,
        List<CabecalhoOrdemTabelaRequest> cabecalhos
) {
}
