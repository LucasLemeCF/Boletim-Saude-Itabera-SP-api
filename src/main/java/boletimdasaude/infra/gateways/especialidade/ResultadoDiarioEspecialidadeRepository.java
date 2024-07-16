package boletimdasaude.infra.gateways.especialidade;

import boletimdasaude.application.gateways.especialidade.IResultadoDiarioEspecialidadeRepository;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoMensalEspecialidadeMapper;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

public class ResultadoDiarioEspecialidadeRepository implements IResultadoDiarioEspecialidadeRepository {

    private final IEspecialidadeRepositoryJpa especialidadeRepositoryJpa;

    public ResultadoDiarioEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepositoryJpa) {
        this.especialidadeRepositoryJpa = especialidadeRepositoryJpa;
    }

//    @Override
//    public ResultadoMensalEspecialidade salvarResultadoDiario(ResultadoMensalEspecialidade resultadoMensalEspecialidade, Especialidade especialidade) {
//        EspecialidadeEntity oldEspecialidadeEntity = especialidadeRepositoryJpa.findById(especialidade.id())
//                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", especialidade.id())));
//
//        ResultadoMensalEspecialidadeEntity novoResultadoMensalEntity = ResultadoMensalEspecialidadeMapper.toEntity(resultadoMensalEspecialidade);
//
//        oldEspecialidadeEntity.getResultadosMensais().add(novoResultadoMensalEntity);
//        novoResultadoMensalEntity.setEspecialidade(oldEspecialidadeEntity);
//
//        especialidadeRepositoryJpa.save(oldEspecialidadeEntity);
//
//        return ResultadoMensalEspecialidadeMapper.toDomain(novoResultadoMensalEntity);
//    }

    @Override
    public ResultadoDiarioEspecialidade salvarResultadoDiario(ResultadoDiarioEspecialidade resultadoDiarioEspecialidade, ResultadoMensalEspecialidade resultadoMensalEspecialidade) {
        return null;
    }
}
