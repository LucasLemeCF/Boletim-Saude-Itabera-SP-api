package boletimMedico.domain.especialidade;

import java.util.Date;

public record ResultadoDiarioEspecialidade(
        Long id,
        Date data,
        int atendimentos,
        String medico
) {
}
