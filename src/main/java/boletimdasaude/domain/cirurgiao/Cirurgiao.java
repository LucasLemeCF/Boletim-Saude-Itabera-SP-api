package boletimdasaude.domain.cirurgiao;

import java.util.List;

public record Cirurgiao(
        Long id,
        String nome,
        List<ProcedimentoCirurgiao> procedimentos
) {
}
