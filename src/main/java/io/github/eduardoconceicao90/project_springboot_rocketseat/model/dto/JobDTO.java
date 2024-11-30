package io.github.eduardoconceicao90.project_springboot_rocketseat.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

    @Schema(description = "Descrição da vaga",
            example = "Desenvolvedor Java",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(description = "Benefícios da vaga",
            example = "Vale transporte, vale refeição",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(description = "Nível da vaga",
            example = "JUNIOR",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
    
}
