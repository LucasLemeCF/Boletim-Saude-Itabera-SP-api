package boletimdasaude.infra.controller.especialidade;
import boletimdasaude.application.useCases.especialidade.EspecialidadeInteractor;
import boletimdasaude.domain.especialidade.Especialidade;
import jakarta.validation.Valid;
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
        Especialidade especialidade = especialidadeInteractor.criarEspecialidade(EspecialidadeMapper.toDomain(request));
        return ResponseEntity.ok().body(especialidade);
    }

    @GetMapping
    public ResponseEntity<List<Especialidade>> buscarTodasEspecialidade() {
        return ResponseEntity.ok().body(especialidadeInteractor.buscarTodasEspecialidades());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Especialidade> editarEspecialidade(
            @PathVariable(value="id") Long id,
            @Valid @RequestBody EspecialidadeRequest request
    ) {
        Especialidade especialidade = especialidadeInteractor.editarEspecialidade(id, EspecialidadeMapper.toDomain(request));
        return ResponseEntity.ok().body(especialidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirEspecialidade(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok().body(especialidadeInteractor.excluirEspecialidade(id));
    }

}
