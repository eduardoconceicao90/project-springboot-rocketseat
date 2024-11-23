package io.github.eduardoconceicao90.project_springboot_rocketseat.service;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.UserFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Company;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company create(Company company){

        this.companyRepository
                .findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);

        return this.companyRepository.save(company);

    }

}
