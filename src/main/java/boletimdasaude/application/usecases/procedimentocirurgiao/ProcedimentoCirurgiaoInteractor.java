package boletimdasaude.application.usecases.procedimentocirurgiao;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.application.gateways.procedimentocirurgiao.IProcedimentoCirurgiaoRepository;
import boletimdasaude.application.responses.procedimentoCirurgiao.ProcedimentoDoCirurgiaoResponse;
import boletimdasaude.application.responses.procedimentoCirurgiao.ProcedimentosDoCirurgiaoResponse;
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

    public ProcedimentosDoCirurgiaoResponse buscarTodosProcedimentosDoCirurgiao(Long id) {
        List<ProcedimentoCirurgiao> procedimentos = new ArrayList<>();
        String nomeCirurgiao = "";

        List<Cirurgiao> listaCirurgioes = cirurgiaoRepository.buscarTodosCirurgioes();

        for (Cirurgiao cirurgiao : listaCirurgioes) {
            if (Objects.equals(cirurgiao.id(), id)) {
                nomeCirurgiao = cirurgiao.nome();
                procedimentos.addAll(cirurgiao.procedimentos());
            }
        }

        List<ProcedimentoDoCirurgiaoResponse> procedimentosResponse = converterProcedimentosResponse(procedimentos);

        return new ProcedimentosDoCirurgiaoResponse(nomeCirurgiao, procedimentosResponse);
    }

    private List<ProcedimentoDoCirurgiaoResponse> converterProcedimentosResponse(List<ProcedimentoCirurgiao> procedimentos) {
        List<ProcedimentoDoCirurgiaoResponse> procedimentosResponse = new ArrayList<>();

        for (ProcedimentoCirurgiao procedimento : procedimentos) {
            procedimentosResponse.add(new ProcedimentoDoCirurgiaoResponse(procedimento.id(), procedimento.nome()));
        }

        return procedimentosResponse;
    }

    public ProcedimentoCirurgiao editarProcedimentoCirurgiao(Long id, ProcedimentoCirurgiao procedimentoCirurgiao, Long cirurgiaoId) {
        return procedimentoCirurgiaoRepository.editarProcedimentoCirurgiao(id, procedimentoCirurgiao, cirurgiaoId);
    }

    public String excluirProcedimentoCirurgiao(Long id) {
            return procedimentoCirurgiaoRepository.excluirProcedimentoCirurgiao(id);
    }
}
