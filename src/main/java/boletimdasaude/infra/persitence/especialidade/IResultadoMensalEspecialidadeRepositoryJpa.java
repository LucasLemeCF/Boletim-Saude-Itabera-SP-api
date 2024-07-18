package boletimdasaude.infra.persitence.especialidade;

import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IResultadoMensalEspecialidadeRepositoryJpa extends JpaRepository<ResultadoMensalEspecialidadeEntity, Long> {

    List<ResultadoMensalEspecialidadeEntity> findByMesAndAno(int mes, int ano);

}
