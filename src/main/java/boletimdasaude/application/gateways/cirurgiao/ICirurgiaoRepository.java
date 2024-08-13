package boletimdasaude.application.gateways.cirurgiao;

import boletimdasaude.domain.cirurgiao.Cirurgiao;

import java.util.List;

public interface ICirurgiaoRepository {

    Cirurgiao criarCirurgiao(Cirurgiao cirurgiao);

    List<Cirurgiao> buscarTodosCirurgioes();

    List<Cirurgiao> buscarTodosCirurgioesComDadosMes(String data);

    Cirurgiao editarCirurgiao(Long id, Cirurgiao cirurgiao);

    String excluirCirurgiao(Long id);

}
