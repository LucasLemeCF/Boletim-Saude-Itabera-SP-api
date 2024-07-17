package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.requests.tabela.TabelaRequest;

public class TabelaInteractor {

    private final SalvarDadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor;
    private final SalvarDadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor;

    public TabelaInteractor(SalvarDadosEspecialidadeInteractor salvarDadosEspecialidadeInteractor, SalvarDadosCirurgiaoInteractor salvarDadosCirurgiaoInteractor) {
        this.salvarDadosEspecialidadeInteractor = salvarDadosEspecialidadeInteractor;
        this.salvarDadosCirurgiaoInteractor = salvarDadosCirurgiaoInteractor;
    }

    public void salvarDadosTabela(TabelaRequest request) {
        salvarDadosEspecialidadeInteractor.salvarDadosEspecialidade(request);
        salvarDadosCirurgiaoInteractor.salvarDadosCirurgiao(request);
    }

}
