package boletimMedico.infra.controller.especialidade;
import boletimMedico.application.useCases.especialidade.EspecialidadeInteractor;
import boletimMedico.domain.especialidade.Especialidade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    private final EspecialidadeInteractor especialidadeInteractor;
    private final EspecialidadeMapper especialidadeMapper;

    public EspecialidadeController(EspecialidadeInteractor especialidadeInteractor, EspecialidadeMapper especialidadeMapper) {
        this.especialidadeInteractor = especialidadeInteractor;
        this.especialidadeMapper = especialidadeMapper;
    }

    @PostMapping
    public ResponseEntity<Especialidade> criarEspecialidade(@RequestBody EspecialidadeRequest request) {
        return ResponseEntity.ok().body(especialidadeInteractor.criarEspecialidade(EspecialidadeMapper.toDomain(request)));
    }

    @GetMapping
    public ResponseEntity<List<Especialidade>> buscarTodasEspecialidade() {
        return ResponseEntity.ok().body(especialidadeInteractor.buscarTodasEspecialidades());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirEspecialidade(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok().body(especialidadeInteractor.excluirEspecialidade(id));
    }

}
