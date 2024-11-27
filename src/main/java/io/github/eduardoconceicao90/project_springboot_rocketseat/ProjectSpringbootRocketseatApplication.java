package io.github.eduardoconceicao90.project_springboot_rocketseat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
			title = "Gestão de Vagas",
			version = "1.0",
			description = "API responsável pela gestão de vagas"
	)
)
@SecurityScheme(
	name = "jwt_auth", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER
)
public class ProjectSpringbootRocketseatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringbootRocketseatApplication.class, args);
	}

}
