package boletimdasaude.infra.persitence.cirurgiao;

import boletimdasaude.infra.persitence.cirurgiao.entities.CirurgiaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICirurgiaoRepositoryJpa extends JpaRepository<CirurgiaoEntity, Long> {
}
