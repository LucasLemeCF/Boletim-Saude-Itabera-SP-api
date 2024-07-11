package boletimdasaude.infra.persitence.tabela;

import boletimdasaude.infra.persitence.tabela.entities.ItemTabelaEspecialidadeEntity;
import boletimdasaude.infra.persitence.tabela.entities.OrdemTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemTabelaEspecialidadeRepositoryJpa extends JpaRepository<ItemTabelaEspecialidadeEntity, Long> {
}
