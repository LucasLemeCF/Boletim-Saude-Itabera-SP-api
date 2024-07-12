package boletimdasaude.infra.controller.ordemtabela.requests;

import java.util.List;

public record EditarTabelaRequest(
        List<LinhaTabelaRequest> linhas,
        List<CabecalhoTabelaRequest> cabecalhos
) {
}
