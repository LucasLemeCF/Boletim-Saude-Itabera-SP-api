package boletimdasaude.domain.cirurgiao;

import java.util.List;

public record ResultadoMensalCirurgiao(
        Long id,
        String mes,
        int ano,
        int atendimentos,
        List<ResultadoDiarioCirurgiao> resultadosDiarios
) {
}
