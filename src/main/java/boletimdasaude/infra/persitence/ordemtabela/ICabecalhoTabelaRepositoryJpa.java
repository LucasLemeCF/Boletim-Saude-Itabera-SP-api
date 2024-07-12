package boletimdasaude.infra.persitence.ordemtabela;

import boletimdasaude.infra.persitence.ordemtabela.entities.CabecalhoTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICabecalhoTabelaRepositoryJpa extends JpaRepository<CabecalhoTabelaEntity, Long> {
}
