package boletimdasaude.infra.controllers;

import boletimdasaude.application.responses.tabela.TabelaResponse;
import boletimdasaude.application.usecases.tabela.TabelaInteractor;
import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.util.ConverterData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/tabela")
public class TabelaController {

    private final TabelaInteractor tabelaInteractor;
    private ConverterData converterData;

    public TabelaController(TabelaInteractor tabelaInteractor) {
        this.tabelaInteractor = tabelaInteractor;
    }

    @PostMapping
    public ResponseEntity<String> salvarDadosTabela(
            @Valid @RequestBody TabelaRequest request
    ) {
        tabelaInteractor.salvarDadosTabela(request);
        return ResponseEntity.ok().body("Tabela salva com sucesso");
    }

    @GetMapping(path = "/{data}")
    public ResponseEntity<TabelaResponse> buscarDadosTabela(
            @PathVariable(value="data") String data
    ) {
        return ResponseEntity.ok().body(tabelaInteractor.buscarDadosTabela(data));
    }

}
