package boletimdasaude.application.responses.ordemTabela;

public record LinhaEspecialidadeResponse(
        Long posicao,
        Long idEspecialidade,
        String nomeEspecialidade
) {
}
