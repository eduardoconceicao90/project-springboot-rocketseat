package io.github.eduardoconceicao90.project_springboot_rocketseat.repository;

import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

    Optional<Job> findByDescription(String description);

    List<Job> findByDescriptionContainingIgnoreCase(String filter);

}
