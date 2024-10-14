package boletimdasaude.application.responses.procedimentoCirurgiao;

import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;

import java.util.List;

public record ProcedimentoDoCirurgiaoResponse(
        Long id,
        String nome
) {
}
