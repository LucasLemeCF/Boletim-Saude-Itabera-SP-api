package boletimMedico.infra.controller.especialidade;

public record EspecialidadeRequest(
        String especialidade,
        String medico,
        int metaDiaria,
        int metaMensal
) {
}
