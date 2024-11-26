package io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProfileCandidateDTO {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String description;

}
