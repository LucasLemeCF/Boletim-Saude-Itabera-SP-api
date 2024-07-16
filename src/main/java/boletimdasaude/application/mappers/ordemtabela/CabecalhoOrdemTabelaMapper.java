package boletimdasaude.application.mappers.ordemtabela;

import boletimdasaude.domain.ordemtabela.CabecalhoTabela;
import boletimdasaude.application.requests.ordemtabela.CabecalhoOrdemTabelaRequest;

import java.util.List;

public class CabecalhoOrdemTabelaMapper {

    private CabecalhoOrdemTabelaMapper() {}

    public static CabecalhoTabela toDomain(CabecalhoOrdemTabelaRequest request) {
        return new CabecalhoTabela(
                null,
                request.posicao(),
                request.tipo(),
                TextoCabecalhoTabelaMapper.toDomainList(request.textos())
        );
    }

    public static List<CabecalhoTabela> toDomainList(List<CabecalhoOrdemTabelaRequest> domainList) {
        return domainList.stream()
                .map(CabecalhoOrdemTabelaMapper::toDomain)
                .toList();
    }

}
