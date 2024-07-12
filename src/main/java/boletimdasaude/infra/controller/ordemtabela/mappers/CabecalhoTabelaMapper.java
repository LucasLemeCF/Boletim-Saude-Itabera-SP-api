package boletimdasaude.infra.controller.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.CabecalhoTabela;
import boletimdasaude.infra.controller.ordemtabela.requests.CabecalhoTabelaRequest;

import java.util.List;

public class CabecalhoTabelaMapper {

    private CabecalhoTabelaMapper() {}

    public static CabecalhoTabela toDomain(CabecalhoTabelaRequest request) {
        return new CabecalhoTabela(
                null,
                request.posicao(),
                request.tipo(),
                TextoCabecalhoTabelaMapper.toDomainList(request.textos())
        );
    }

    public static List<CabecalhoTabela> toDomainList(List<CabecalhoTabelaRequest> domainList) {
        return domainList.stream()
                .map(CabecalhoTabelaMapper::toDomain)
                .toList();
    }

}
