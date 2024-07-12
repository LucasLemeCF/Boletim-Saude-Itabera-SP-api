package boletimdasaude.infra.persitence.ordemtabela;

import boletimdasaude.infra.persitence.ordemtabela.entities.TextoCabecalhoTabelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITextoCabecalhoTabelaRepositoryJpa extends JpaRepository<TextoCabecalhoTabelaEntity, Long> {
}
