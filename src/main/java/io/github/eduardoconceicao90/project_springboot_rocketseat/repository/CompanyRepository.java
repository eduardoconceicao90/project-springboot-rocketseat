package io.github.eduardoconceicao90.project_springboot_rocketseat.repository;

import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByUsernameOrEmail(String username, String email);

    Optional<Company> findByUsername(String username);

}
