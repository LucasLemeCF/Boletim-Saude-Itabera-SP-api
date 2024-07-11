package boletimdasaude.application.usecases.cirurgiao;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.domain.cirurgiao.Cirurgiao;

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

    public Cirurgiao editarCirurgiao(Long id, Cirurgiao cirurgiao) {
        return cirurgiaoRepository.editarCirurgiao(id, cirurgiao);
    }

    public String excluirCirurgiao(Long id) {
        return cirurgiaoRepository.excluirCirurgiao(id);
    }

}
