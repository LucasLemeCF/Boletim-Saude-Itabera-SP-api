package boletimdasaude.infra.controller.ordemtabela.requests;

import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.ordemtabela.TextoCabecalhoTabela;

import java.util.List;

public record CabecalhoTabelaRequest(
        Long posicao,
        TipoLinha tipo,
        List<TextoCabecalhoTabelaRequest> textos
) {
}
