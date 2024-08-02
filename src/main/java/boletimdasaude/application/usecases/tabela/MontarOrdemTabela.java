package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.mappers.ordemtabela.CabecalhoOrdemTabelaMapper;
import boletimdasaude.application.mappers.ordemtabela.EditarOrdemTabelaMapper;
import boletimdasaude.application.mappers.ordemtabela.LinhaOrdemTabelaMapper;
import boletimdasaude.application.requests.ordemtabela.CabecalhoOrdemTabelaRequest;
import boletimdasaude.application.requests.ordemtabela.EditarOrdemTabelaRequest;
import boletimdasaude.application.requests.ordemtabela.LinhaOrdemTabelaRequest;
import boletimdasaude.application.requests.ordemtabela.TextoCabecalhoOrdemTabelaRequest;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.usecases.ordemtabela.EditarOrdemTabelaInteractor;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.domain.enums.TipoLinha;

import java.util.ArrayList;
import java.util.List;

public class MontarOrdemTabela {

    private final EditarOrdemTabelaInteractor editarTabelaInteractor;

    public MontarOrdemTabela(EditarOrdemTabelaInteractor editarTabelaInteractor) {
        this.editarTabelaInteractor = editarTabelaInteractor;
    }

    public void converterOrdemTabela(TabelaRequest request) {
        EditarOrdemTabelaRequest editarOrdemTabelaRequest = new EditarOrdemTabelaRequest(
                request.data(),
                montarLinhas(request.linhas()),
                request.cabecalhos()
        );

        OrdemTabela ordemTabela = new OrdemTabela(
                null,
                editarOrdemTabelaRequest.data(),
                false,
                LinhaOrdemTabelaMapper.toDomainList(editarOrdemTabelaRequest.linhas()),
                CabecalhoOrdemTabelaMapper.toDomainList(editarOrdemTabelaRequest.cabecalhos())
        );

        editarTabelaInteractor.adicionarOrdemTabela(ordemTabela);
    }

    private List<LinhaOrdemTabelaRequest> montarLinhas(List<LinhaTabelaRequest> linhas) {
        List<LinhaOrdemTabelaRequest> resultado = new ArrayList<>();

        for (LinhaTabelaRequest linha : linhas) {
            resultado.add(new LinhaOrdemTabelaRequest(
                    linha.posicao(),
                    linha.tipo(),
                    linha.componenteId()
            ));
        }

        return resultado;
    }

}
