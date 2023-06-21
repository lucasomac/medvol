package br.com.lucasomac.medvol.infra;

import br.com.lucasomac.medvol.domain.consumer.Consumer;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Consumer consumer) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("API Voll.med").withSubject(consumer.getLogin()).withExpiresAt(expirationDate()).sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error to generate JWT token", exception);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusMinutes(20).toInstant(ZoneOffset.of("-03:00"));
    }
}