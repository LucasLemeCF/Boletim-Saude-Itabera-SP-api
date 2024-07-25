package boletimdasaude.application.gateways.ordemtabela;

public interface ILinhaTabelaEspecialidadeRepository {

    Long buscarPosicaoEspecialidade(Long especialidadeId);

    Long buscarPosicaoProcedimento(Long procedimento);

}
