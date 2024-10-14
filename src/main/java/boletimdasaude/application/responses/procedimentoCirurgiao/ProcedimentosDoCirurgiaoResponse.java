package boletimdasaude.application.responses.procedimentoCirurgiao;

import java.util.List;

public record ProcedimentosDoCirurgiaoResponse(
        String nomeCirurgiao,
        List<ProcedimentoDoCirurgiaoResponse> procedimentos
) {
}
