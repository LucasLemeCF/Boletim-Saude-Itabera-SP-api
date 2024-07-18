package boletimdasaude.application.requests.procedimentocirurgiao;

public record ProcedimentoCirurgiaoRequest(
        String nome,
        Long cirurgiaoId
) {
}
