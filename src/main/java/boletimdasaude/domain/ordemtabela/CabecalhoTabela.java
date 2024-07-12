package boletimdasaude.domain.ordemtabela;

import boletimdasaude.domain.enums.TipoLinha;

import java.util.List;

public record CabecalhoTabela(
        Long id,
        Long posicao,
        TipoLinha tipo,
        List<TextoCabecalhoTabela> textos
) {
}
