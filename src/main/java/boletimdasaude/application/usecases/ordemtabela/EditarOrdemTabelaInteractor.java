package boletimdasaude.application.usecases.ordemtabela;

import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.domain.ordemtabela.OrdemTabela;

public class EditarOrdemTabelaInteractor {

    private final IOrdemTabelaRepository tabelaRepository;

    public EditarOrdemTabelaInteractor(IOrdemTabelaRepository tabelaRepository) {
        this.tabelaRepository = tabelaRepository;
    }

    public OrdemTabela adicionarOrdemTabela(OrdemTabela ordemTabela) {
        return tabelaRepository.adicionarOrdemTabela(ordemTabela);
    }

    public OrdemTabela editarOrdemTabela(OrdemTabela ordemTabela) {
        return tabelaRepository.editarOrdemTabela(ordemTabela);
    }

    public OrdemTabela buscarOrdemTabela(String data) {
        return tabelaRepository.buscarOrdemTabela(data);
    }

}
