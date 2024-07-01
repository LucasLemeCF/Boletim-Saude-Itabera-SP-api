package boletimMedico.infra.persitence.especialidade;

import boletimMedico.infra.persitence.especialidade.entities.EspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadeRepositoryJpa extends JpaRepository<EspecialidadeEntity, Long> {
}
