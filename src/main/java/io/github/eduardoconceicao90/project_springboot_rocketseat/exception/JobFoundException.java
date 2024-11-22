package io.github.eduardoconceicao90.project_springboot_rocketseat.exception;

public class JobFoundException extends RuntimeException{

    public JobFoundException() {
        super("Job já existe");
    }

}
