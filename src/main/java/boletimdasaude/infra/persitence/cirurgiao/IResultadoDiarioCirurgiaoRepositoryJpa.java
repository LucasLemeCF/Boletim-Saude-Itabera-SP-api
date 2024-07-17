package boletimdasaude.infra.persitence.cirurgiao;

import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoDiarioCirurgiaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResultadoDiarioCirurgiaoRepositoryJpa extends JpaRepository<ResultadoDiarioCirurgiaoEntity, Long> {
}
