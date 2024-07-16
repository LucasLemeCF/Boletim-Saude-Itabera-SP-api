package boletimdasaude.application.gateways.especialidade;

import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.Date;

public interface IResultadoMensalEspecialidadeRepository {

    ResultadoMensalEspecialidade salvarDadosIniciaisDoMes(ResultadoMensalEspecialidade resultadoMensalEspecialidade, Long especialidadeId);

    ResultadoMensalEspecialidade salvarDadosDoDia(ResultadoDiarioEspecialidade resultadoDiarioEspecialidade, Long especialidadeId);

    ResultadoMensalEspecialidadeEntity buscarMesAnoEspecialidade(Date data, Long especialidadeId);

}
