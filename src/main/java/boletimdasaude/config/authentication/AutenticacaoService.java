package boletimdasaude.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${login.usuario}")
    private String usuario;

    @Value("${login.senha}")
    private String senha;

    public AutenticacaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(usuario)) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new User(usuario, passwordEncoder.encode(senha), Collections.emptyList());
    }
}
