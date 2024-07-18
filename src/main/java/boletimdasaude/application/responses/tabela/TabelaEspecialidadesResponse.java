package boletimdasaude.application.responses.tabela;

public record TabelaEspecialidadesResponse(
        Long especialidadeId,
        String especialidade,
        int pacientesAtendidosDia,
        int metaDiaria,
        int pacientesAtendidosMes,
        int metaMensal
) {
}
