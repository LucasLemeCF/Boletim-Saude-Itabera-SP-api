package boletimdasaude.infra.persitence.ordemtabela;

import boletimdasaude.infra.persitence.ordemtabela.entities.DataOrdemTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDataOrdemTabelaRepositoryJpa extends JpaRepository<DataOrdemTabelaEntity, Long> {
    List<DataOrdemTabelaEntity> findAllByData(String data);
}
