package boletimdasaude.application.usecases.cirurgiao;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.application.responses.cirurgiao.CirurgiaoResponse;
import boletimdasaude.application.responses.especialidade.EspecialidadeResponse;
import boletimdasaude.domain.cirurgiao.Cirurgiao;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;
import boletimdasaude.domain.especialidade.Especialidade;

import java.util.ArrayList;
import java.util.List;

public class CirurgiaoInteractor {

    private final ICirurgiaoRepository cirurgiaoRepository;

    public CirurgiaoInteractor(ICirurgiaoRepository cirurgiaoRepository) {
        this.cirurgiaoRepository = cirurgiaoRepository;
    }

    public Cirurgiao criarCirurgiao(Cirurgiao cirurgiao) {
        return cirurgiaoRepository.criarCirurgiao(cirurgiao);
    }

    public List<Cirurgiao> buscarTodosCirurgioes() {
        return cirurgiaoRepository.buscarTodosCirurgioes();
    }

    public List<CirurgiaoResponse> buscarTodosNomesDeCirurgioes() {
        List<CirurgiaoResponse> cirurgioesResponse = new ArrayList<>();

        List<Cirurgiao> cirurgioes = cirurgiaoRepository.buscarTodosCirurgioes();

        for (Cirurgiao cirurgiao : cirurgioes) {
            cirurgioesResponse.add(new CirurgiaoResponse(cirurgiao.id(), cirurgiao.nome()));
        }

        return cirurgioesResponse;
    }

    public List<Cirurgiao> buscarTodosCirurgioesComDadosMes(String data) {
        return cirurgiaoRepository.buscarTodosCirurgioesComDadosMes(data);
    }

    public int[] buscarResultadosDoAno(int ano) {
        int[] resultados = new int[12];

        List<Cirurgiao> cirurgioes = cirurgiaoRepository.buscarTodosCirurgioes();

        for (int i = 1; i <= 12; i++) {
            for (Cirurgiao cirurgiao : cirurgioes) {
                for (ProcedimentoCirurgiao procedimento : cirurgiao.procedimentos()) {
                    if (procedimento.resultadosMensais() != null && !procedimento.nome().equals("Procedimento Anestésico")) {
                        for (ResultadoMensalCirurgiao resultadoMensal : procedimento.resultadosMensais()) {
                            if (resultadoMensal.mes() == i && resultadoMensal.ano() == ano) {
                                resultados[i - 1] += resultadoMensal.atendimentos();
                            }
                        }
                    }
                }
            }
        }

        return resultados;
    }

    public Cirurgiao editarCirurgiao(Long id, Cirurgiao cirurgiao) {
        return cirurgiaoRepository.editarCirurgiao(id, cirurgiao);
    }

    public String excluirCirurgiao(Long id) {
        return cirurgiaoRepository.excluirCirurgiao(id);
    }

}
