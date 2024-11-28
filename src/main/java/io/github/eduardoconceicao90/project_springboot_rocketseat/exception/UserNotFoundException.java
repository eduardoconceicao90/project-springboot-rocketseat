package io.github.eduardoconceicao90.project_springboot_rocketseat.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not found");
    }

}
