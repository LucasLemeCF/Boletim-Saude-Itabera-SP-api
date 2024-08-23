package boletimdasaude.infra.controllers;

import boletimdasaude.domain.usuario.DadosAutenticacao;
import boletimdasaude.infra.security.DadosTokenJWT;
import boletimdasaude.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private AuthenticationManager manager;

    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken(authentication.getName());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
