package boletimdasaude;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API para gerenciamento de boletins de Itaberá"))
public class BoletimMedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoletimMedicoApplication.class, args);
	}

}
