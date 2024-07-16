package boletimdasaude.infra.persitence.especialidade;

import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResultadoMensalEspecialidadeRepositoryJpa extends JpaRepository<ResultadoMensalEspecialidadeEntity, Long> {
}
