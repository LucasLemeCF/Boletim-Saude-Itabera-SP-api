package boletimdasaude.infra.persitence.tabela;

import boletimdasaude.infra.persitence.tabela.entities.ItemTabelaCirurgiaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemTabelaCirurgiaoRepositoryJpa extends JpaRepository<ItemTabelaCirurgiaoEntity, Long> {
}
