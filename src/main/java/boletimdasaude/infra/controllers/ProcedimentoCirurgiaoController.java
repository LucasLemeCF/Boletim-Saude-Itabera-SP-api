package boletimdasaude.infra.controllers;
import boletimdasaude.application.mappers.procedimentocirurgiao.ProcedimentoCirurgiaoMapper;
import boletimdasaude.application.requests.procedimentocirurgiao.ProcedimentoCirurgiaoRequest;
import boletimdasaude.application.usecases.procedimentocirurgiao.ProcedimentoCirurgiaoInteractor;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedimentoCirurgiao")
public class ProcedimentoCirurgiaoController {

    private final ProcedimentoCirurgiaoInteractor procedimentoProcedimentoCirurgiaoInteractor;

    public ProcedimentoCirurgiaoController(ProcedimentoCirurgiaoInteractor procedimentoProcedimentoCirurgiaoInteractor) {
        this.procedimentoProcedimentoCirurgiaoInteractor = procedimentoProcedimentoCirurgiaoInteractor;
    }

    @PostMapping
    public ResponseEntity<ProcedimentoCirurgiao> criarProcedimentoCirurgiao(@RequestBody ProcedimentoCirurgiaoRequest request) {
        ProcedimentoCirurgiao procedimentoProcedimentoCirurgiao = procedimentoProcedimentoCirurgiaoInteractor.criarProcedimentoCirurgiao(ProcedimentoCirurgiaoMapper.toDomain(request), request.cirurgiaoId());
        return ResponseEntity.ok().body(procedimentoProcedimentoCirurgiao);
    }

    @GetMapping
    public ResponseEntity<List<ProcedimentoCirurgiao>> buscarTodosCirurgioes() {
        return ResponseEntity.ok().body(procedimentoProcedimentoCirurgiaoInteractor.buscarTodosProcedimentosCirurgioes());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ProcedimentoCirurgiao> editarProcedimentoCirurgiao(
            @PathVariable(value="id") Long id,
            @Valid @RequestBody ProcedimentoCirurgiaoRequest request
    ) {
        ProcedimentoCirurgiao procedimentoProcedimentoCirurgiao = procedimentoProcedimentoCirurgiaoInteractor.editarProcedimentoCirurgiao(id, ProcedimentoCirurgiaoMapper.toDomain(request));
        return ResponseEntity.ok().body(procedimentoProcedimentoCirurgiao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProcedimentoCirurgiao(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok().body(procedimentoProcedimentoCirurgiaoInteractor.excluirProcedimentoCirurgiao(id));
    }

}
