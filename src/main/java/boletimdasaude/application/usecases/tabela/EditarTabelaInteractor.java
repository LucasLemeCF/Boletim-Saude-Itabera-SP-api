package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.gateways.tabela.ITabelaRepository;
import boletimdasaude.domain.tabela.OrdemTabela;

public class EditarTabelaInteractor {

    private final ITabelaRepository tabelaRepository;

    public EditarTabelaInteractor(ITabelaRepository tabelaRepository) {
        this.tabelaRepository = tabelaRepository;
    }

    public OrdemTabela editarTabela(OrdemTabela ordemTabela) {
        return tabelaRepository.editarTabela(ordemTabela);
    }

}
