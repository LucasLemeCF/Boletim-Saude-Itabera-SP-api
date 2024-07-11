package boletimdasaude.infra.controller.tabela.requests;

import boletimdasaude.domain.enums.TipoLinha;

public record EspecialidadeTabelaRequest(
        Long posicao,
        TipoLinha tipo,
        Long especialidadeId
) {
}
