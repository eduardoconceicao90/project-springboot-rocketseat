package io.github.eduardoconceicao90.project_springboot_rocketseat.repository;

import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    Optional<Candidate> findByUsernameOrEmail(String username, String email);

    Optional<Candidate> findByUsername(String username);

}
