package boletimMedico.infra.controller.especialidade;

public record EspecialidadeResponse(
        Long id,
        String especialidade,
        String medico,
        int metaDiaria,
        int metaMensal
) {
}
