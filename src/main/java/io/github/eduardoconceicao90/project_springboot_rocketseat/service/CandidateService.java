package io.github.eduardoconceicao90.project_springboot_rocketseat.service;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.JobNotFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.UserFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.UserNotFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Candidate;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Job;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.ApplyJobRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CandidateRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Candidate create(Candidate candidate){

        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return this.candidateRepository.save(candidate);

    }

    public List<Job> findByDescription(String filter) {
        return jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }

    public void execute(UUID idCandidate, UUID idJob) {
        //Validar se o candidato existe
        candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        //Validar se a vaga existe
        jobRepository.findById(idJob).orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        //Adicionar o candidato na vaga
    }

}
