package boletimdasaude.infra.controller.tabela;

import boletimdasaude.application.usecases.tabela.EditarTabelaInteractor;
import boletimdasaude.domain.tabela.OrdemTabela;
import boletimdasaude.infra.controller.tabela.mappers.EditarTabelaMapper;
import boletimdasaude.infra.controller.tabela.requests.EditarTabelaRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tabela")
public class EditarTabelaController {

    private final EditarTabelaInteractor editarTabelaInteractor;

    public EditarTabelaController(EditarTabelaInteractor editarTabelaInteractor) {
        this.editarTabelaInteractor = editarTabelaInteractor;
    }

    @PostMapping
    public ResponseEntity<OrdemTabela> editarTabela(
            @Valid @RequestBody EditarTabelaRequest request
    ) {
        OrdemTabela ordemTabela = editarTabelaInteractor.editarTabela(EditarTabelaMapper.toDomain(request));
        return ResponseEntity.ok().body(ordemTabela);
    }

}
