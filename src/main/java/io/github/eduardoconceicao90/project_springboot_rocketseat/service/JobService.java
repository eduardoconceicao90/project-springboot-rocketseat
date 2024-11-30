package io.github.eduardoconceicao90.project_springboot_rocketseat.service;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.JobFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Job;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job create(Job job){

        this.jobRepository
                .findByDescription(job.getDescription())
                .ifPresent((user) -> {
                    throw new JobFoundException();
                });

        return this.jobRepository.save(job);

    }
}
