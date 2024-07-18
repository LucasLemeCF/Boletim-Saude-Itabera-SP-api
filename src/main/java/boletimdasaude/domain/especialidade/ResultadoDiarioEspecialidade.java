package boletimdasaude.domain.especialidade;

public record ResultadoDiarioEspecialidade(
        Long id,
        int dia,
        int atendimentos,
        String medico
) {
}
