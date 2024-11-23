package io.github.eduardoconceicao90.project_springboot_rocketseat.service;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.UserFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Candidate;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

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

}
