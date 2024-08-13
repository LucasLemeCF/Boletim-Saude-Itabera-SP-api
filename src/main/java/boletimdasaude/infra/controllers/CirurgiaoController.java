package boletimdasaude.infra.controllers;
import boletimdasaude.application.mappers.cirurgiao.CirurgiaoMapper;
import boletimdasaude.application.requests.cirurgiao.CirurgiaoRequest;
import boletimdasaude.application.usecases.cirurgiao.CirurgiaoInteractor;
import boletimdasaude.domain.cirurgiao.Cirurgiao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cirurgiao")
public class CirurgiaoController {

    private final CirurgiaoInteractor cirurgiaoInteractor;

    public CirurgiaoController(CirurgiaoInteractor cirurgiaoInteractor) {
        this.cirurgiaoInteractor = cirurgiaoInteractor;
    }

    @PostMapping
    public ResponseEntity<Cirurgiao> criarCirurgiao(@RequestBody CirurgiaoRequest request) {
        Cirurgiao cirurgiao = cirurgiaoInteractor.criarCirurgiao(CirurgiaoMapper.toDomain(request));
        return ResponseEntity.ok().body(cirurgiao);
    }

    @GetMapping
    public ResponseEntity<List<Cirurgiao>> buscarTodosCirurgioes() {
        return ResponseEntity.ok().body(cirurgiaoInteractor.buscarTodosCirurgioes());
    }

    @GetMapping(path = "/{data}")
    public ResponseEntity<List<Cirurgiao>> buscarTodosCirurgioesComDadosMes(
            @PathVariable(value="data") String data
    ) {
        return ResponseEntity.ok().body(cirurgiaoInteractor.buscarTodosCirurgioesComDadosMes(data));
    }

    @GetMapping(path = "/resultadoAno/{ano}")
    public ResponseEntity<int[]> buscarResultadosDoAno(
            @PathVariable(value="ano") int ano
    ) {
        return ResponseEntity.ok().body(cirurgiaoInteractor.buscarResultadosDoAno(ano));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Cirurgiao> editarCirurgiao(
            @PathVariable(value="id") Long id,
            @Valid @RequestBody CirurgiaoRequest request
    ) {
        Cirurgiao cirurgiao = cirurgiaoInteractor.editarCirurgiao(id, CirurgiaoMapper.toDomain(request));
        return ResponseEntity.ok().body(cirurgiao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCirurgiao(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok().body(cirurgiaoInteractor.excluirCirurgiao(id));
    }

}
