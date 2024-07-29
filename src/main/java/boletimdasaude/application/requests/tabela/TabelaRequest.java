package boletimdasaude.application.requests.tabela;

import boletimdasaude.application.requests.ordemtabela.CabecalhoOrdemTabelaRequest;

import java.util.List;

public record TabelaRequest(
        String data,
        List<LinhaTabelaRequest> linhas,
        List<CabecalhoOrdemTabelaRequest> cabecalhos
) {
}
