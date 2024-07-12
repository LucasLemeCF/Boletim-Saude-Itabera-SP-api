package boletimdasaude.infra.persitence.ordemtabela;

import boletimdasaude.infra.persitence.ordemtabela.entities.LinhaTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILinhaTabelaRepositoryJpa extends JpaRepository<LinhaTabelaEntity, Long> {
}
