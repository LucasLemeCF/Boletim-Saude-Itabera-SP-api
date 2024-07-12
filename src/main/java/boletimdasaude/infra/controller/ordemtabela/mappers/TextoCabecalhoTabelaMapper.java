package boletimdasaude.infra.controller.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.TextoCabecalhoTabela;
import boletimdasaude.infra.controller.ordemtabela.requests.TextoCabecalhoTabelaRequest;

import java.util.List;

public class TextoCabecalhoTabelaMapper {

    private TextoCabecalhoTabelaMapper() {}

    public static TextoCabecalhoTabela toDomain(TextoCabecalhoTabelaRequest request) {
        return new TextoCabecalhoTabela(
                null,
                request.texto()
        );
    }

    public static List<TextoCabecalhoTabela> toDomainList(List<TextoCabecalhoTabelaRequest> domainList) {
        return domainList.stream()
                .map(TextoCabecalhoTabelaMapper::toDomain)
                .toList();
    }

}
