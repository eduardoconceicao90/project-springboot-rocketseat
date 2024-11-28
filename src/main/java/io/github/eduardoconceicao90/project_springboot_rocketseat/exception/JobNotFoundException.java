package io.github.eduardoconceicao90.project_springboot_rocketseat.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(){
        super("Job not found");
    }

}
