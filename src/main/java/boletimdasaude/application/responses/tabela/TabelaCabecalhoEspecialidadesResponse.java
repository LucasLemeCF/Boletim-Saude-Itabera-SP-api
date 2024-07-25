package boletimdasaude.application.responses.tabela;

import boletimdasaude.domain.ordemtabela.TextoCabecalhoTabela;

import java.util.List;

public record TabelaCabecalhoEspecialidadesResponse(
        Long posicao,
        List<TextoCabecalhoTabela> textos,
        List<TabelaEspecialidadesResponse> especialidades
) {
}
