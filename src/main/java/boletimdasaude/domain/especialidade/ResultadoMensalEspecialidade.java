package boletimdasaude.domain.especialidade;

import java.util.List;

public record ResultadoMensalEspecialidade(
        Long id,
        int mes,
        int ano,
        int atendimentos,
        int metaDiaria,
        int metaMensal,
        List<ResultadoDiarioEspecialidade> resultadosDiarios
) {
}
