package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.responses.tabela.*;

public class TabelaInteractor {

    private final DadosEspecialidadeInteractor dadosEspecialidadeInteractor;
    private final DadosCirurgiaoInteractor dadosCirurgiaoInteractor;
    private final MontarOrdemTabela montarOrdemTabela;

    public TabelaInteractor(DadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor, DadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor, MontarOrdemTabela montarOrdemTabela) {
        this.dadosEspecialidadeInteractor = salvarDadosEspecialidadeInteractor;
        this.dadosCirurgiaoInteractor = salvarDadosCirurgiaoInteractor;
        this.montarOrdemTabela = montarOrdemTabela;
    }

    public void salvarDadosTabela(TabelaRequest request) {
        dadosEspecialidadeInteractor.salvarDadosEspecialidade(request);
        dadosCirurgiaoInteractor.salvarDadosCirurgiao(request);
        montarOrdemTabela.converterOrdemTabela(request);
    }

    public TabelaResponse buscarDadosTabela(String data) {
        return new TabelaResponse(
            data,
            dadosEspecialidadeInteractor.organizarDadosEspecialidades(data),
            dadosCirurgiaoInteractor.organizarDadosCirurgioes(data)
        );
    }

}
