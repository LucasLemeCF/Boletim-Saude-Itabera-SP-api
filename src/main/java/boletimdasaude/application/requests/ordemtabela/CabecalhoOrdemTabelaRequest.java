package boletimdasaude.application.requests.ordemtabela;

import boletimdasaude.domain.enums.TipoLinha;

import java.util.List;

public record CabecalhoOrdemTabelaRequest(
        Long posicao,
        TipoLinha tipo,
        List<TextoCabecalhoOrdemTabelaRequest> textos
) {
}
