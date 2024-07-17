package boletimdasaude.domain.cirurgiao;

import java.util.List;

public record ResultadoMensalCirurgiao(
        Long id,
        int mes,
        int ano,
        int atendimentos,
        List<ResultadoDiarioCirurgiao> resultadosDiarios
) {
}
