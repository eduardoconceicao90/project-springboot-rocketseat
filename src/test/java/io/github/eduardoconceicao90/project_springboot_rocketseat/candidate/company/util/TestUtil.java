package io.github.eduardoconceicao90.project_springboot_rocketseat.candidate.company.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtil {

    public static String generateToken(UUID idCompany, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        return JWT.create()
                .withIssuer("javagas")
                .withClaim("type", "company")
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(expiresIn)
                .withSubject(idCompany.toString())
                .sign(algorithm);
    }
}
