package boletimdasaude.application.responses.tabela;

import java.util.List;

public record TabelaResponse(
        String data,
        List<TabelaCabecalhoEspecialidadesResponse> especialidadesCabecalhos,
        List<TabelaCabecalhoCirurgioesResponse> cirurgioesCabecalhos
) {
}
