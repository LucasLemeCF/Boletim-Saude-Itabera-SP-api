package boletimdasaude.application.gateways.tabela;

import boletimdasaude.domain.tabela.OrdemTabela;

public interface ITabelaRepository {

    OrdemTabela editarTabela(OrdemTabela ordemTabela);

}
