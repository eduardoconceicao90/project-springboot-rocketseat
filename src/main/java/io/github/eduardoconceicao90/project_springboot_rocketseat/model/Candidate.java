package io.github.eduardoconceicao90.project_springboot_rocketseat.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Candidate {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String description;
    private String curriculum;

}
