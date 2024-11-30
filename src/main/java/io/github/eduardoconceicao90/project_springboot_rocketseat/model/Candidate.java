package io.github.eduardoconceicao90.project_springboot_rocketseat.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(description = "Nome do candidato",
            example = "Eduardo Conceição",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(description = "Username do candidato",
            example = "eduardoconceicao",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Schema(description = "E-mail do candidato",
            example = "eduardo@mail.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @Schema(description = "Senha do candidato",
            example = "1234567890",
            minLength = 10, maxLength = 100,
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Descrição do candidato",
            example = "Desenvolvedor Java",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
