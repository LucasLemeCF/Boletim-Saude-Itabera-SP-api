package boletimdasaude.application.gateways.tabela;

import boletimdasaude.domain.especialidade.Especialidade;

import java.util.Optional;

public interface ITabelaEspecialidadeRepository {

    Optional<Especialidade> buscarEspecialidade(Long especialidadeId);

}
