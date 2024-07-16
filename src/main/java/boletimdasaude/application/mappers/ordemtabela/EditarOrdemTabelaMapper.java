package boletimdasaude.application.mappers.ordemtabela;

import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.application.requests.ordemtabela.EditarOrdemTabelaRequest;

public class EditarOrdemTabelaMapper {

    private EditarOrdemTabelaMapper() {
    }

    public static OrdemTabela toDomain(EditarOrdemTabelaRequest request) {
        return new OrdemTabela(
                null,
                LinhaOrdemTabelaMapper.toDomainList(request.linhas()),
                CabecalhoOrdemTabelaMapper.toDomainList(request.cabecalhos())
        );
    }

}
