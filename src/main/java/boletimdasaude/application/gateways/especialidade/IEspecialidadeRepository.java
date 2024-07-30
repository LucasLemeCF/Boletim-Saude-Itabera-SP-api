package boletimdasaude.application.gateways.especialidade;

import boletimdasaude.domain.especialidade.Especialidade;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadeRepository {

    Especialidade criarEspecialidade(Especialidade especialidade);

    List<Especialidade> buscarTodasEspecialidades();

    Optional<Especialidade> buscarEspecialidade(Long especialidadeId);

    Especialidade editarEspecialidade(Long id, Especialidade especialidade);

    String excluirEspecialidade(Long id);

}
