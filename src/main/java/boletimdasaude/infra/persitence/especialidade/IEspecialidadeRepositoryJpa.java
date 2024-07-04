package boletimdasaude.infra.persitence.especialidade;

import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadeRepositoryJpa extends JpaRepository<EspecialidadeEntity, Long> {
}
