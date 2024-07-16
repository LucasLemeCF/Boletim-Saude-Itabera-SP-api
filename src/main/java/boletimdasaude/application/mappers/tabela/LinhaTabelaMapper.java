package boletimdasaude.application.mappers.tabela;

import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;

import java.util.Date;
import java.util.List;

public class LinhaTabelaMapper {

    public static ResultadoDiarioEspecialidade toDomain(LinhaTabelaRequest request, Date data) {
        return new ResultadoDiarioEspecialidade (
                null,
                data,
                request.pacientesAtendidos(),
                null
        );
    }

    public static List<ResultadoDiarioEspecialidade> toDomainList(List<LinhaTabelaRequest> domainList, Date data) {
        return domainList.stream()
                .map(request -> toDomain(request, data))
                .toList();
    }

}
