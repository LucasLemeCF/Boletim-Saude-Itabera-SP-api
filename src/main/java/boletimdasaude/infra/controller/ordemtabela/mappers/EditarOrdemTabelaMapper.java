package boletimdasaude.infra.controller.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.controller.ordemtabela.requests.EditarTabelaRequest;

public class EditarOrdemTabelaMapper {

    private EditarOrdemTabelaMapper() {
    }

    public static OrdemTabela toDomain(EditarTabelaRequest request) {
        return new OrdemTabela(
                null,
                LinhaTabelaMapper.toDomainList(request.linhas()),
                CabecalhoTabelaMapper.toDomainList(request.cabecalhos())
        );
    }

}
