package boletimdasaude.infra.persitence.ordemtabela;

import boletimdasaude.infra.persitence.ordemtabela.entities.DataOrdemTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataOrdemTabelaRepositoryJpa extends JpaRepository<DataOrdemTabelaEntity, Long> {
    DataOrdemTabelaEntity findByData(String data);
}
