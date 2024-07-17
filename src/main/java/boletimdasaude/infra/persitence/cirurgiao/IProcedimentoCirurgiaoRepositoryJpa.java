package boletimdasaude.infra.persitence.cirurgiao;

import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProcedimentoCirurgiaoRepositoryJpa extends JpaRepository<ProcedimentoCirurgiaoEntity, Long> {
}
