package boletimdasaude.infra.controllers;

import boletimdasaude.application.usecases.ordemtabela.EditarOrdemTabelaInteractor;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.application.mappers.ordemtabela.EditarOrdemTabelaMapper;
import boletimdasaude.application.requests.ordemtabela.EditarOrdemTabelaRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordem-tabela")
public class EditarTabelaController {

    private final EditarOrdemTabelaInteractor editarTabelaInteractor;

    public EditarTabelaController(EditarOrdemTabelaInteractor editarTabelaInteractor) {
        this.editarTabelaInteractor = editarTabelaInteractor;
    }

    @PostMapping
    public ResponseEntity<OrdemTabela> editarTabela(
            @Valid @RequestBody EditarOrdemTabelaRequest request
    ) {
        OrdemTabela ordemTabela = editarTabelaInteractor.editarOrdemTabela(EditarOrdemTabelaMapper.toDomain(request));
        return ResponseEntity.ok().body(ordemTabela);
    }

    @GetMapping(path = "/{data}")
    public ResponseEntity<OrdemTabela> buscarOrdemTabela(
            @PathVariable(value="data") String data
    ) {
        return ResponseEntity.ok().body(editarTabelaInteractor.buscarOrdemTabela(data));
    }

}
