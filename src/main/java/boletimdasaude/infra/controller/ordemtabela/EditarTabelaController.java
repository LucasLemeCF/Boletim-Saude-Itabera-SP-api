package boletimdasaude.infra.controller.ordemtabela;

import boletimdasaude.application.usecases.ordemtabela.EditarOrdemTabelaInteractor;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.controller.ordemtabela.mappers.EditarOrdemTabelaMapper;
import boletimdasaude.infra.controller.ordemtabela.requests.EditarTabelaRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tabela")
public class EditarTabelaController {

    private final EditarOrdemTabelaInteractor editarTabelaInteractor;

    public EditarTabelaController(EditarOrdemTabelaInteractor editarTabelaInteractor) {
        this.editarTabelaInteractor = editarTabelaInteractor;
    }

    @PostMapping
    public ResponseEntity<OrdemTabela> editarTabela(
            @Valid @RequestBody EditarTabelaRequest request
    ) {
        OrdemTabela ordemTabela = editarTabelaInteractor.editarOrdemTabela(EditarOrdemTabelaMapper.toDomain(request));
        return ResponseEntity.ok().body(ordemTabela);
    }

}
