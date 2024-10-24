package boletimdasaude.application.responses.ordemTabela;

import java.util.List;

public record CabecalhoResponse(
        Long posicao,
        List<String> textos
) {
}
