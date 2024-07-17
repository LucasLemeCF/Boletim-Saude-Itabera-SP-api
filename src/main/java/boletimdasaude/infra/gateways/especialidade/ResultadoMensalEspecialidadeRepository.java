package boletimdasaude.infra.gateways.especialidade;

import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoDiarioEspecialidadeMapper;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoMensalEspecialidadeMapper;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IResultadoMensalEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.Date;
import java.util.Optional;

public class ResultadoMensalEspecialidadeRepository implements IResultadoMensalEspecialidadeRepository {

    private final IEspecialidadeRepositoryJpa especialidadeRepositoryJpa;
    private final IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa;

    public ResultadoMensalEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepositoryJpa, IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa) {
        this.especialidadeRepositoryJpa = especialidadeRepositoryJpa;
        this.resultadoMensalEspecialidadeRepositoryJpa = resultadoMensalEspecialidadeRepositoryJpa;
    }

    @Override
    public ResultadoMensalEspecialidade salvarDadosIniciaisDoMes(ResultadoMensalEspecialidade resultadoMensalEspecialidade, Long especialidadeId) {
        EspecialidadeEntity oldEspecialidadeEntity = especialidadeRepositoryJpa.findById(especialidadeId)
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", especialidadeId)));

        ResultadoMensalEspecialidadeEntity novoResultadoMensalEntity = ResultadoMensalEspecialidadeMapper.toEntity(resultadoMensalEspecialidade);
        novoResultadoMensalEntity.getResultadosDiarios().forEach(resultadoDiario -> resultadoDiario.setResultadoMensal(novoResultadoMensalEntity));

        oldEspecialidadeEntity.getResultadosMensais().add(novoResultadoMensalEntity);
        novoResultadoMensalEntity.setEspecialidade(oldEspecialidadeEntity);

        especialidadeRepositoryJpa.save(oldEspecialidadeEntity);

        return ResultadoMensalEspecialidadeMapper.toDomain(novoResultadoMensalEntity);
    }

    @Override
    public ResultadoMensalEspecialidade salvarDadosDoDia(ResultadoDiarioEspecialidade resultadoDiarioEspecialidade, Long especialidadeId) {
        ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity = buscarMesAnoEspecialidade(resultadoDiarioEspecialidade.data(), especialidadeId);
        ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity = ResultadoDiarioEspecialidadeMapper.toEntity(resultadoDiarioEspecialidade);
        resultadoDiarioEspecialidadeEntity.setResultadoMensal(resultadoMensalEspecialidadeEntity);

        resultadoMensalEspecialidadeEntity.getResultadosDiarios().add(resultadoDiarioEspecialidadeEntity);
        ResultadoMensalEspecialidadeEntity resultado = resultadoMensalEspecialidadeRepositoryJpa.save(resultadoMensalEspecialidadeEntity);

        return ResultadoMensalEspecialidadeMapper.toDomain(resultado);
    }

    @Override
    public ResultadoMensalEspecialidadeEntity buscarMesAnoEspecialidade(Date data, Long especialidadeId) {
        ResultadoMensalEspecialidadeEntity resultado = null;

        Optional<EspecialidadeEntity> especialidadeEntity = especialidadeRepositoryJpa.findById(especialidadeId);

        int mes = ConverterData.toMes(data);
        int ano = ConverterData.toAno(data);

        if (especialidadeEntity.isPresent()) {
            for(ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity : especialidadeEntity.get().getResultadosMensais()) {
                if (resultadoMensalEspecialidadeEntity.getMes() == mes && resultadoMensalEspecialidadeEntity.getAno() == ano) {
                    resultado = resultadoMensalEspecialidadeEntity;
                }
            }
        }

        return resultado;
    }

}
