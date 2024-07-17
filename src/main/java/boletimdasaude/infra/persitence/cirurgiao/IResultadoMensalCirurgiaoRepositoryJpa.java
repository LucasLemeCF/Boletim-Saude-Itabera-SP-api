package boletimdasaude.infra.persitence.cirurgiao;

import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResultadoMensalCirurgiaoRepositoryJpa extends JpaRepository<ResultadoMensalCirurgiaoEntity, Long> {
}
