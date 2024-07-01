package boletimMedico.application.useCases.especialidade;

import boletimMedico.application.gateways.especialidade.IEspecialidadeRepository;
import boletimMedico.domain.especialidade.Especialidade;

import java.util.List;

public class EspecialidadeInteractor {

    private final IEspecialidadeRepository especialidadeRepository;

    public EspecialidadeInteractor(IEspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    public Especialidade criarEspecialidade(Especialidade especialidade) {
        return especialidadeRepository.criarEspecialidade(especialidade);
    }

    public List<Especialidade> buscarTodasEspecialidades() {
        return especialidadeRepository.buscarTodasEspecialidades();
    }

    public String excluirEspecialidade(Long id) {
        return especialidadeRepository.excluirEspecialidade(id);
    }

}
