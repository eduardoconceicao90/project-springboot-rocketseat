package io.github.eduardoconceicao90.project_springboot_rocketseat.security.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CandidateRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto.AuthCandidateDTO;
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
public class AuthCandidate {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenDTO execute(AuthCandidateDTO authCandidateDTO) throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(authCandidateDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Candidate not found"));

        // Verificar se a senha informada é igual a senha do banco de dados
        var passwordMatches = passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

        // Se a senha informada não for igual a senha do banco de dados, lançar exceção
        if(!passwordMatches){
            throw new AuthenticationException("Invalid password");
        }

        // Se a senha informada for igual a senha do banco de dados, autenticar usuário
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(15));

        var token = JWT.create()
                                .withIssuer("javagas")
                                .withClaim("type", "candidate")
                                .withClaim("roles", Arrays.asList("CANDIDATE"))
                                .withExpiresAt(expiresIn)
                                .withSubject(candidate.getId().toString())
                                .sign(algorithm);

        return TokenDTO.builder()
                                .access_token(token)
                                .expires_in(expiresIn.toEpochMilli())
                                .build();
    }
}
