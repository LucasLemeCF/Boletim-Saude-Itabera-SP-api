package boletimdasaude.infra.persitence.tabela;

import boletimdasaude.infra.persitence.tabela.entities.OrdemTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITabelaRepositoryJpa extends JpaRepository<OrdemTabelaEntity, Long> {
}
