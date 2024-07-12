package boletimdasaude.application.usecases.ordemtabela;

import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.domain.ordemtabela.OrdemTabela;

import java.util.List;

public class EditarOrdemTabelaInteractor {

    private final IOrdemTabelaRepository tabelaRepository;

    public EditarOrdemTabelaInteractor(IOrdemTabelaRepository tabelaRepository) {
        this.tabelaRepository = tabelaRepository;
    }

    public OrdemTabela editarOrdemTabela(OrdemTabela ordemTabela) {
        return tabelaRepository.editarOrdemTabela(ordemTabela);
    }

    public List<OrdemTabela> buscarOrdemTabela() {
        return tabelaRepository.buscarOrdemTabela();
    }

}
