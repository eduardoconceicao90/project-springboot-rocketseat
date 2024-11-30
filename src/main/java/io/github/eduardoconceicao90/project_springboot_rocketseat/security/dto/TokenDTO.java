package io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {

    private String access_token;
    private Long expires_in;

}
