package boletimdasaude.application.usecases.procedimentocirurgiao;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.application.gateways.procedimentocirurgiao.IProcedimentoCirurgiaoRepository;
import boletimdasaude.domain.cirurgiao.Cirurgiao;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcedimentoCirurgiaoInteractor {

    private final IProcedimentoCirurgiaoRepository procedimentoCirurgiaoRepository;
    private final ICirurgiaoRepository cirurgiaoRepository;

    public ProcedimentoCirurgiaoInteractor(IProcedimentoCirurgiaoRepository procedimentoCirurgiaoRepository, ICirurgiaoRepository cirurgiaoRepository) {
        this.procedimentoCirurgiaoRepository = procedimentoCirurgiaoRepository;
        this.cirurgiaoRepository = cirurgiaoRepository;
    }

    public ProcedimentoCirurgiao criarProcedimentoCirurgiao(ProcedimentoCirurgiao procedimentoCirurgiao, Long cirurgiaoId) {
        return procedimentoCirurgiaoRepository.criarProcedimentoCirurgiao(procedimentoCirurgiao, cirurgiaoId);
    }

    public List<ProcedimentoCirurgiao> buscarTodosProcedimentosCirurgioes() {
        return procedimentoCirurgiaoRepository.buscarTodosProcedimentosCirurgioes();
    }

    public List<ProcedimentoCirurgiao> buscarTodosProcedimentosDoCirurgiao(Long id) {
        List<ProcedimentoCirurgiao> resultado = new ArrayList<>();

        List<Cirurgiao> listaCirurgioes = cirurgiaoRepository.buscarTodosCirurgioes();

        for (Cirurgiao cirurgiao : listaCirurgioes) {
            if (Objects.equals(cirurgiao.id(), id)) {
                resultado.addAll(cirurgiao.procedimentos());
            }
        }

        return resultado;
    }

    public ProcedimentoCirurgiao editarProcedimentoCirurgiao(Long id, ProcedimentoCirurgiao procedimentoCirurgiao, Long cirurgiaoId) {
        return procedimentoCirurgiaoRepository.editarProcedimentoCirurgiao(id, procedimentoCirurgiao, cirurgiaoId);
    }

    public String excluirProcedimentoCirurgiao(Long id) {
            return procedimentoCirurgiaoRepository.excluirProcedimentoCirurgiao(id);
    }
}
