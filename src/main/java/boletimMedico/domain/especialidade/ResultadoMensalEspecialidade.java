package boletimMedico.domain.especialidade;

import java.util.List;

public record ResultadoMensalEspecialidade(
        Long id,
        int ano,
        int atendimentos,
        int metaDiaria,
        int metaMensal,
        List<ResultadoDiarioEspecialidade> resultadosDiarios
) {
}
