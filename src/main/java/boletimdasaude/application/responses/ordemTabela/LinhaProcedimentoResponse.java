package boletimdasaude.application.responses.ordemTabela;

public record LinhaProcedimentoResponse(
        Long posicao,
        Long idProcedimento,
        String nomeCirurgiao,
        String nomeProcedimento
) {
}
