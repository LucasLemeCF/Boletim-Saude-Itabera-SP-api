package boletimdasaude.application.responses.tabela;

public record TabelaCirurgioesResponse(
        Long posicao,
        Long procedimentoId,
        String cirurgiao,
        String procedimento,
        int pacientesAtendidosDia,
        int pacientesAtendidosMes,
        int pacientesAtendidosAno
) {
}
