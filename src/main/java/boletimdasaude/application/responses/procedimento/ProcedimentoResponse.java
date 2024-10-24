package boletimdasaude.application.responses.procedimento;

public record ProcedimentoResponse(
        Long id,
        String cirurgiao,
        String procedimento
) {
}
