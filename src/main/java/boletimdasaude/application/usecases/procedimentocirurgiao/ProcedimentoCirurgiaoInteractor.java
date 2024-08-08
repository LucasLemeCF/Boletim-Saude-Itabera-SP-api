package boletimdasaude.application.usecases.procedimentocirurgiao;

import boletimdasaude.application.gateways.procedimentocirurgiao.IProcedimentoCirurgiaoRepository;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;

import java.util.List;

public class ProcedimentoCirurgiaoInteractor {

    private final IProcedimentoCirurgiaoRepository procedimentoCirurgiaoRepository;

    public ProcedimentoCirurgiaoInteractor(IProcedimentoCirurgiaoRepository procedimentoCirurgiaoRepository) {
        this.procedimentoCirurgiaoRepository = procedimentoCirurgiaoRepository;
    }

    public ProcedimentoCirurgiao criarProcedimentoCirurgiao(ProcedimentoCirurgiao procedimentoCirurgiao, Long cirurgiaoId) {
        return procedimentoCirurgiaoRepository.criarProcedimentoCirurgiao(procedimentoCirurgiao, cirurgiaoId);
    }

    public List<ProcedimentoCirurgiao> buscarTodosProcedimentosCirurgioes() {
        return procedimentoCirurgiaoRepository.buscarTodosProcedimentosCirurgioes();
    }

    public ProcedimentoCirurgiao editarProcedimentoCirurgiao(Long id, ProcedimentoCirurgiao procedimentoCirurgiao, Long cirurgiaoId) {
        return procedimentoCirurgiaoRepository.editarProcedimentoCirurgiao(id, procedimentoCirurgiao, cirurgiaoId);
    }

    public String excluirProcedimentoCirurgiao(Long id) {
            return procedimentoCirurgiaoRepository.excluirProcedimentoCirurgiao(id);
    }
}
