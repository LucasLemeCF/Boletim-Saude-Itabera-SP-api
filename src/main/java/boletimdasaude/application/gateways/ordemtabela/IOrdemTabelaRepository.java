package boletimdasaude.application.gateways.ordemtabela;

import boletimdasaude.application.responses.tabela.TabelaCabecalhoCirurgioesResponse;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoEspecialidadesResponse;
import boletimdasaude.domain.ordemtabela.OrdemTabela;

import java.util.List;

public interface IOrdemTabelaRepository {

    OrdemTabela adicionarOrdemTabela(OrdemTabela ordemTabela);

    OrdemTabela editarOrdemTabela(OrdemTabela ordemTabela);

    OrdemTabela buscarOrdemTabela(String data);

    List<TabelaCabecalhoEspecialidadesResponse> buscarCabecalhosEspecialidades(String data);

    List<TabelaCabecalhoCirurgioesResponse> buscarCabecalhosCirurgioes(String data);

}