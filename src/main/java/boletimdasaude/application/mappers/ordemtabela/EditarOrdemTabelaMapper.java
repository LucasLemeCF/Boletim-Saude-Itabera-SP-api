package boletimdasaude.application.mappers.ordemtabela;

import boletimdasaude.domain.ordemtabela.DataOrdemTabela;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.application.requests.ordemtabela.EditarOrdemTabelaRequest;

import java.util.ArrayList;
import java.util.List;

public class EditarOrdemTabelaMapper {

    private EditarOrdemTabelaMapper() {
    }

    public static OrdemTabela toDomain(EditarOrdemTabelaRequest request) {
        List<DataOrdemTabela> listaDatas = new ArrayList<>();
        listaDatas.add(new DataOrdemTabela(null, request.data()));

        return new OrdemTabela(
                null,
                listaDatas,
                true,
                LinhaOrdemTabelaMapper.toDomainList(request.linhas()),
                CabecalhoOrdemTabelaMapper.toDomainList(request.cabecalhos())
        );
    }

}
