package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.requests.tabela.TabelaRequest;

public class TabelaInteractor {

    private final SalvarDadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor;

    public TabelaInteractor(SalvarDadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor) {
        this.salvarDadosEspecialidadeInteractor = salvarDadosEspecialidadeInteractor;
    }

    public void salvarDadosTabela(TabelaRequest request) {
        salvarDadosEspecialidadeInteractor.salvarDadosEspecialidade(request);
    }

}
