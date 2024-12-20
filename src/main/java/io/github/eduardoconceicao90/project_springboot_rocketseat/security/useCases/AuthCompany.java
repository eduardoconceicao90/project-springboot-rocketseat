package io.github.eduardoconceicao90.project_springboot_rocketseat.security.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CompanyRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto.AuthCompanyDTO;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompany {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Company not found"));

        // Verificar se a senha informada é igual a senha do banco de dados
        var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // Se a senha informada não for igual a senha do banco de dados, lançar exceção
        if(!passwordMatches){
            throw new AuthenticationException("Invalid password");
        }

        // Se a senha informada for igual a senha do banco de dados, autenticar usuário
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                                .withIssuer("javagas")
                                .withClaim("type", "company")
                                .withClaim("roles", Arrays.asList("COMPANY"))
                                .withExpiresAt(expiresIn)
                                .withSubject(company.getId().toString())
                                .sign(algorithm);

        return TokenDTO.builder()
                        .access_token(token)
                        .expires_in(expiresIn.toEpochMilli())
                        .build();

    }
}
