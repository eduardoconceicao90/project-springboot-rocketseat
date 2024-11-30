package io.github.eduardoconceicao90.project_springboot_rocketseat.repository;

import io.github.eduardoconceicao90.project_springboot_rocketseat.model.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob, UUID> {
}
