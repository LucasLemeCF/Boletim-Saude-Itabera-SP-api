package boletimdasaude.infra.controller.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.LinhaTabela;
import boletimdasaude.infra.controller.ordemtabela.requests.LinhaTabelaRequest;

import java.util.List;
import java.util.stream.Collectors;

public class LinhaTabelaMapper {

    public static LinhaTabela toDomain(LinhaTabelaRequest request) {
        return new LinhaTabela(
                null,
                request.posicao(),
                request.tipo(),
                request.componenteId()
        );
    }

    public static List<LinhaTabela> toDomainList(List<LinhaTabelaRequest> domainList) {
        return domainList.stream()
                .map(LinhaTabelaMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
