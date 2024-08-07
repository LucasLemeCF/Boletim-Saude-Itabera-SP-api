package boletimdasaude.application.usecases.especialidade;

import boletimdasaude.application.gateways.especialidade.IEspecialidadeRepository;
import boletimdasaude.domain.especialidade.Especialidade;

import java.util.ArrayList;
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

    public List<Especialidade> buscarTodasEspecialidadesComDadosMes(String data) {
        return especialidadeRepository.buscarTodasEspecialidadesComDadosMes(data);
    }

    public Especialidade editarEspecialidade(Long id, Especialidade especialidade) {
        return especialidadeRepository.editarEspecialidade(id, especialidade);
    }

    public String excluirEspecialidade(Long id) {
        return especialidadeRepository.excluirEspecialidade(id);
    }

}
