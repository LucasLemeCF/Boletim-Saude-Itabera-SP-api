package boletimdasaude.infra.controllers;
import boletimdasaude.application.mappers.procedimentocirurgiao.ProcedimentoCirurgiaoMapper;
import boletimdasaude.application.requests.procedimentocirurgiao.ProcedimentoCirurgiaoRequest;
import boletimdasaude.application.responses.procedimento.ProcedimentoResponse;
import boletimdasaude.application.responses.procedimentoCirurgiao.ProcedimentosDoCirurgiaoResponse;
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
    public ResponseEntity<List<ProcedimentoCirurgiao>> buscarTodosProcedimentosCirurgioes() {
        return ResponseEntity.ok().body(procedimentoProcedimentoCirurgiaoInteractor.buscarTodosProcedimentosCirurgioes());
    }

    @GetMapping(path = "/nomes")
    public ResponseEntity<List<ProcedimentoResponse>> buscarTodosNomesDeProcedimentos() {
        return ResponseEntity.ok().body(procedimentoProcedimentoCirurgiaoInteractor.buscarTodosNomesDeProcedimentos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProcedimentosDoCirurgiaoResponse> buscarTodosProcedimentosDoCirurgiao(
            @PathVariable(value="id") Long id) {
        ProcedimentosDoCirurgiaoResponse procedimentosDoCirurgiao = procedimentoProcedimentoCirurgiaoInteractor.buscarTodosProcedimentosDoCirurgiao(id);
        return ResponseEntity.ok().body(procedimentosDoCirurgiao);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ProcedimentoCirurgiao> editarProcedimentoCirurgiao(
            @PathVariable(value="id") Long id,
            @Valid @RequestBody ProcedimentoCirurgiaoRequest request
    ) {
        ProcedimentoCirurgiao procedimentoCirurgiao = procedimentoProcedimentoCirurgiaoInteractor.editarProcedimentoCirurgiao(id, ProcedimentoCirurgiaoMapper.toDomain(request), request.cirurgiaoId());
        return ResponseEntity.ok().body(procedimentoCirurgiao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProcedimentoCirurgiao(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok().body(procedimentoProcedimentoCirurgiaoInteractor.excluirProcedimentoCirurgiao(id));
    }

}
