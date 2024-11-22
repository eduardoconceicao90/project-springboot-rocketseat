package io.github.eduardoconceicao90.project_springboot_rocketseat.exception;

public class UserFoundException extends RuntimeException{

    public UserFoundException() {
        super("Usuário já existe");
    }

}
