package boletimMedico.infra.gateways.especialidade;

import boletimMedico.application.gateways.especialidade.IEspecialidadeRepository;
import boletimMedico.domain.especialidade.Especialidade;
import boletimMedico.exceptions.NotFoundException;
import boletimMedico.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimMedico.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;

import java.util.List;

public class EspecialidadeRepository implements IEspecialidadeRepository {

    private final IEspecialidadeRepositoryJpa especialidadeRepository;
    private final EspecialidadeEntityMapper mapper;

    public EspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepository, EspecialidadeEntityMapper mapper) {
        this.especialidadeRepository = especialidadeRepository;
        this.mapper = mapper;
    }

    @Override
    public Especialidade criarEspecialidade(Especialidade especialidade) {
        return EspecialidadeEntityMapper.toDomain(especialidadeRepository.save(mapper.toEntity(especialidade)));
    }

    @Override
    public List<Especialidade> buscarTodasEspecialidades() {
        return mapper.toDomainList(especialidadeRepository.findAll());
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