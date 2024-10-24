package boletimdasaude.application.responses.ordemTabela;

import java.util.List;

public record CirurgiaoResponse(
        String nomeCirurgiao,
        List<LinhaProcedimentoResponse> linhasProcedimentos
) {
}
