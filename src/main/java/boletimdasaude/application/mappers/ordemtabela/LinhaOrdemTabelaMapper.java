package boletimdasaude.application.mappers.ordemtabela;

import boletimdasaude.domain.ordemtabela.LinhaTabela;
import boletimdasaude.application.requests.ordemtabela.LinhaOrdemTabelaRequest;

import java.util.List;
import java.util.stream.Collectors;

public class LinhaOrdemTabelaMapper {

    public static LinhaTabela toDomain(LinhaOrdemTabelaRequest request) {
        return new LinhaTabela(
                null,
                request.posicao(),
                request.tipo(),
                request.componenteId()
        );
    }

    public static List<LinhaTabela> toDomainList(List<LinhaOrdemTabelaRequest> domainList) {
        return domainList.stream()
                .map(LinhaOrdemTabelaMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
