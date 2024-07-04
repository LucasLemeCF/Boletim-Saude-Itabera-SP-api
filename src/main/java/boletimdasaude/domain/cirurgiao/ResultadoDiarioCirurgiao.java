package boletimdasaude.domain.cirurgiao;

import java.util.Date;

public record ResultadoDiarioCirurgiao(
        Long id,
        Date data,
        int atendimentos
) {
}
