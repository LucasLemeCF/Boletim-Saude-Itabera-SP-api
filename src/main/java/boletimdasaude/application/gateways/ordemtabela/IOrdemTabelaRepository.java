package boletimdasaude.application.gateways.ordemtabela;

import boletimdasaude.domain.ordemtabela.OrdemTabela;

import java.util.List;

public interface IOrdemTabelaRepository {

    OrdemTabela editarOrdemTabela(OrdemTabela ordemTabela);

    OrdemTabela buscarOrdemTabela(String data);

}
