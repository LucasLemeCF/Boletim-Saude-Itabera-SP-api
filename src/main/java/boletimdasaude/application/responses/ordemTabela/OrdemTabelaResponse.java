package boletimdasaude.application.responses.ordemTabela;

import java.util.List;

public record OrdemTabelaResponse(
        String data,
        List<CabecalhoResponse> cabecalhosEspecialidades,
        List<CabecalhoResponse> cabecalhosCirurgioes,
        List<LinhaEspecialidadeResponse> linhasEspecialidades,
        List<CirurgiaoResponse> listaCirurgioes
) {
}
