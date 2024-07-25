package boletimdasaude.application.responses.tabela;

import boletimdasaude.domain.ordemtabela.TextoCabecalhoTabela;

import java.util.List;

public record TabelaCabecalhoCirurgioesResponse(
        Long posicao,
        List<TextoCabecalhoTabela> textos,
        List<TabelaCirurgioesResponse> cirurgioes
) {
}
