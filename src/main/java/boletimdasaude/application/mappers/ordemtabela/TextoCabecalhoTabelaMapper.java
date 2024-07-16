package boletimdasaude.application.mappers.ordemtabela;

import boletimdasaude.domain.ordemtabela.TextoCabecalhoTabela;
import boletimdasaude.application.requests.ordemtabela.TextoCabecalhoOrdemTabelaRequest;

import java.util.List;

public class TextoCabecalhoTabelaMapper {

    private TextoCabecalhoTabelaMapper() {}

    public static TextoCabecalhoTabela toDomain(TextoCabecalhoOrdemTabelaRequest request) {
        return new TextoCabecalhoTabela(
                null,
                request.texto()
        );
    }

    public static List<TextoCabecalhoTabela> toDomainList(List<TextoCabecalhoOrdemTabelaRequest> domainList) {
        return domainList.stream()
                .map(TextoCabecalhoTabelaMapper::toDomain)
                .toList();
    }

}
