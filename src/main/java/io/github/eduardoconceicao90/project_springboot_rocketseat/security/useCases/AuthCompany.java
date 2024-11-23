package io.github.eduardoconceicao90.project_springboot_rocketseat.security.useCases;

import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CompanyRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto.AuthCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthCompany {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Company not found"));

        // Verificar se a senha informada é igual a senha do banco de dados
        var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // Se a senha informada não for igual a senha do banco de dados, lançar exceção
        if(!passwordMatches){
            throw new AuthenticationException("Invalid password");
        }

        // Se a senha informada for igual a senha do banco de dados, autenticar usuário

    }
}
