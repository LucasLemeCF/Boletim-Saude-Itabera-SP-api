package boletimdasaude.domain.cirurgiao;

import java.util.Date;

public record ResultadoDiarioCirurgiao(
        Long id,
        int data,
        int atendimentos
) {
}
