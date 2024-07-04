package boletimdasaude.infra.gateways.especialidade;

import boletimdasaude.application.gateways.especialidade.IEspecialidadeRepository;
import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoMensalEspecialidadeMapper;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;

import java.util.List;

public class EspecialidadeRepository implements IEspecialidadeRepository {

    private final IEspecialidadeRepositoryJpa especialidadeRepository;
    private final EspecialidadeEntityMapper especialidadeEntityMapper;
    private final ResultadoMensalEspecialidadeMapper resultadoMensalEspecialidadeMapper;

    public EspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepository, EspecialidadeEntityMapper especialidadeEntityMapper, ResultadoMensalEspecialidadeMapper resultadoMensalEspecialidadeMapper) {
        this.especialidadeRepository = especialidadeRepository;
        this.especialidadeEntityMapper = especialidadeEntityMapper;
        this.resultadoMensalEspecialidadeMapper = resultadoMensalEspecialidadeMapper;
    }

    @Override
    public Especialidade criarEspecialidade(Especialidade especialidade) {
        return EspecialidadeEntityMapper.toDomain(especialidadeRepository.save(especialidadeEntityMapper.toEntity(especialidade)));
    }

    @Override
    public List<Especialidade> buscarTodasEspecialidades() {
        return especialidadeEntityMapper.toDomainList(especialidadeRepository.findAll());
    }

    @Override
    public Especialidade editarEspecialidade(Long id, Especialidade especialidade) {
        EspecialidadeEntity oldEntity = especialidadeRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não econtrado", id))
        );

        EspecialidadeEntity newEntity = new EspecialidadeEntity(
                id,
                especialidade.especialidade() != null ? especialidade.especialidade() : oldEntity.getEspecialidade(),
                especialidade.medicoAtual() != null ? especialidade.medicoAtual() : oldEntity.getMedicoAtual(),
                especialidade.metaDiariaAtual() != 0 ? especialidade.metaDiariaAtual() : oldEntity.getMetaDiariaAtual(),
                especialidade.metaMensalAtual() != 0 ? especialidade.metaMensalAtual() : oldEntity.getMetaMensalAtual(),
                resultadoMensalEspecialidadeMapper.toEntityList(especialidade.resultadosMensais())
        );

        return EspecialidadeEntityMapper.toDomain(especialidadeRepository.save(newEntity));
    }

    @Override
    public String excluirEspecialidade(Long id) {
        return especialidadeRepository.findById(id)
                .map(especialidade -> {
                    especialidadeRepository.delete(especialidade);
                    return "Especialidade excluida com sucesso";
                })
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", id)));
    }

}