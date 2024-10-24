package boletimdasaude.application.responses.cirurgiao;

import java.util.List;

public record CirurgiaoResponse(
        Long id,
        String cirurgiao,
        List<ProcedimentoCirurgiaoResponse> procedimentos
) {
}
