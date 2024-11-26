package io.github.eduardoconceicao90.project_springboot_rocketseat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
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
public class ProjectSpringbootRocketseatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringbootRocketseatApplication.class, args);
	}

}
