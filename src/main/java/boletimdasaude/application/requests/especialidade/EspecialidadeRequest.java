package boletimdasaude.application.requests.especialidade;

public record EspecialidadeRequest(
        String especialidade,
        String medico,
        int metaDiaria,
        int metaMensal
) {
}
