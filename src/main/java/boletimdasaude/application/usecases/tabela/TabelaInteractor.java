package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.responses.tabela.*;

public class TabelaInteractor {

    private final DadosEspecialidadeInteractor dadosEspecialidadeInteractor;
    private final DadosCirurgiaoInteractor dadosCirurgiaoInteractor;

    public TabelaInteractor(DadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor, DadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor) {
        this.dadosEspecialidadeInteractor = salvarDadosEspecialidadeInteractor;
        this.dadosCirurgiaoInteractor = salvarDadosCirurgiaoInteractor;
    }

    public void salvarDadosTabela(TabelaRequest request) {
        dadosEspecialidadeInteractor.salvarDadosEspecialidade(request);
        dadosCirurgiaoInteractor.salvarDadosCirurgiao(request);
    }

    public TabelaResponse buscarDadosTabela(String data) {
        return new TabelaResponse(
            data,
            dadosEspecialidadeInteractor.organizarDadosEspecialidades(data),
            dadosCirurgiaoInteractor.organizarDadosCirurgioes(data)
        );
    }

}
