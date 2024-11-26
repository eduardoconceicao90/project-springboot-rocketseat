package io.github.eduardoconceicao90.project_springboot_rocketseat.security.useCases;

import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CandidateRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto.ProfileCandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidate{

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateDTO execute(UUID idCandidate){
        var candidate = candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        return ProfileCandidateDTO.builder()
                                        .id(candidate.getId())
                                        .name(candidate.getName())
                                        .username(candidate.getUsername())
                                        .email(candidate.getEmail())
                                        .description(candidate.getDescription())
                                        .build();
    }

}
