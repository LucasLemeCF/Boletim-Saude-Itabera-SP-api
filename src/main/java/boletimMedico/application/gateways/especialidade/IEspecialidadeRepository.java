package boletimMedico.application.gateways.especialidade;

import boletimMedico.domain.especialidade.Especialidade;

import java.util.List;

public interface IEspecialidadeRepository {

    Especialidade criarEspecialidade(Especialidade especialidade);

    List<Especialidade> buscarTodasEspecialidades();

    String excluirEspecialidade(Long id);

}
