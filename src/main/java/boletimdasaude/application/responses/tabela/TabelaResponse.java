package boletimdasaude.application.responses.tabela;

import java.util.List;

public record TabelaResponse(
        List<TabelaEspecialidadesResponse> especialidades,
        List<TabelaCirurgioesResponse> cirurgioes
) {
}
