package boletimdasaude.infra.persitence.cirurgiao;

import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IResultadoMensalCirurgiaoRepositoryJpa extends JpaRepository<ResultadoMensalCirurgiaoEntity, Long> {

    List<ResultadoMensalCirurgiaoEntity> findByMesAndAno(int mes, int ano);

}
