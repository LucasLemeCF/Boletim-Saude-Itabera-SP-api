package boletimdasaude.domain.cirurgiao;

import java.util.List;

public record ProcedimentoCirurgiao(
        Long id,
        String nome,
        List<ResultadoMensalCirurgiao> resultadosMensais
) {
}
