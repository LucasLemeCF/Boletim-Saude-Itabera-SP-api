package boletimdasaude.infra.controller.tabela.mappers;

import boletimdasaude.domain.tabela.ItemTabelaCirurgiao;
import boletimdasaude.infra.controller.tabela.requests.CirurgiaoTabelaRequest;

import java.util.List;

public class CirurgiaoTabelaMapper {

    private CirurgiaoTabelaMapper() {}

    public static ItemTabelaCirurgiao toDomain(CirurgiaoTabelaRequest request) {
        return new ItemTabelaCirurgiao(
                null,
                request.posicao(),
                request.tipo(),
                request.procedimentoId()
        );
    }

    public static List<ItemTabelaCirurgiao> toDomainList(List<CirurgiaoTabelaRequest> domainList) {
        return domainList.stream()
                .map(CirurgiaoTabelaMapper::toDomain)
                .toList();
    }

}
