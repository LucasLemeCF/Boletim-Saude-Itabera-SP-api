package boletimdasaude.domain.especialidade;

import java.util.List;

public record Especialidade(
    Long id,
    String especialidade,
    String medicoAtual,
    int metaDiariaAtual,
    int metaMensalAtual,
    List<ResultadoMensalEspecialidade> resultadosMensais
) {
}
