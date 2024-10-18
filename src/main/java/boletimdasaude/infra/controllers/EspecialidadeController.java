package boletimdasaude.infra.controllers;
import boletimdasaude.application.mappers.especialidade.EspecialidadeMapper;
import boletimdasaude.application.requests.especialidade.EspecialidadeRequest;
import boletimdasaude.application.responses.especialidade.EspecialidadeResponse;
import boletimdasaude.application.usecases.especialidade.EspecialidadeInteractor;
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

    @GetMapping(path = "/nomes")
    public ResponseEntity<List<EspecialidadeResponse>> buscarTodosNomesDeEspecialidade() {
        return ResponseEntity.ok().body(especialidadeInteractor.buscarTodosNomesDeEspecialidade());
    }

    @GetMapping(path = "/{data}")
    public ResponseEntity<List<Especialidade>> buscarTodasEspecialidadeRelatorio(
            @PathVariable(value="data") String data
    ) {
        return ResponseEntity.ok().body(especialidadeInteractor.buscarTodasEspecialidadesComDadosMes(data));
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
