package boletimdasaude.infra.persitence.ordemtabela;

import boletimdasaude.infra.persitence.ordemtabela.entities.OrdemTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdemTabelaRepositoryJpa extends JpaRepository<OrdemTabelaEntity, Long> {
    OrdemTabelaEntity findByData(String data);
}
