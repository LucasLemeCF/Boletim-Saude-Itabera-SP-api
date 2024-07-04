package boletimdasaude.application.gateways.especialidade;

import boletimdasaude.domain.especialidade.Especialidade;

import java.util.List;

public interface IEspecialidadeRepository {

    Especialidade criarEspecialidade(Especialidade especialidade);

    List<Especialidade> buscarTodasEspecialidades();

    Especialidade editarEspecialidade(Long id, Especialidade especialidade);

    String excluirEspecialidade(Long id);

}
