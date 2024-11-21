package io.github.eduardoconceicao90.project_springboot_rocketseat.controller;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.UserFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Candidate;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping
    public Candidate create(@Valid @RequestBody Candidate candidate){

        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidate);
    }

}
