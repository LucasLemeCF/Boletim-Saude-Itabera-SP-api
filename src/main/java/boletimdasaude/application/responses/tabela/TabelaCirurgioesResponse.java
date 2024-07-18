package boletimdasaude.application.responses.tabela;

public record TabelaCirurgioesResponse(
        Long procedimentoId,
        String cirurgioes,
        int pacientesAtendidosDia,
        int pacientesAtendidosMes,
        int pacientesAtendidosAno
) {
}
