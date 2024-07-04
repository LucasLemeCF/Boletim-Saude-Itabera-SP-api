package boletimdasaude.infra.controller.cirurgiao;
import boletimdasaude.application.useCases.cirurgiao.CirurgiaoInteractor;
import boletimdasaude.domain.cirurgiao.Cirurgiao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cirurgiao")
public class CirurgiaoController {

    private final CirurgiaoInteractor cirurgiaoInteractor;
    //    private final CirurgiaoMapper cirurgiaoMapper;

    public CirurgiaoController(CirurgiaoInteractor cirurgiaoInteractor) {
        this.cirurgiaoInteractor = cirurgiaoInteractor;
    }

    @PostMapping
    public ResponseEntity<Cirurgiao> criarCirurgiao(@RequestBody CirurgiaoRequest request) {
        Cirurgiao cirurgiao = cirurgiaoInteractor.criarCirurgiao(CirurgiaoMapper.toDomain(request));
        return ResponseEntity.ok().body(cirurgiao);
    }

//    @GetMapping
//    public ResponseEntity<List<Cirurgiao>> buscarTodasCirurgiao() {
//        return ResponseEntity.ok().body(cirurgiaoInteractor.buscarTodasCirurgiaos());
//    }

//    @PatchMapping(path = "/{id}")
//    public ResponseEntity<Cirurgiao> editarCirurgiao(
//            @PathVariable(value="id") Long id,
//            @Valid @RequestBody CirurgiaoRequest request
//    ) {
//        Cirurgiao cirurgiao = cirurgiaoInteractor.editarCirurgiao(id, CirurgiaoMapper.toDomain(request));
//        return ResponseEntity.ok().body(cirurgiao);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> excluirCirurgiao(@PathVariable(value="id") Long id) {
//        return ResponseEntity.ok().body(cirurgiaoInteractor.excluirCirurgiao(id));
//    }

}
