package io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

    private String username;
    private String password;

}
