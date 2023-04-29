package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import med.voll.api.domain.usuario.User;

@Service
public class TokenService {
    
    public String generateToken(User user) {
        var algorithm = Algorithm.HMAC256("12345678");
        return JWT.create()
            .withIssuer("medvoll")
            .withSubject(user.getLogin())
            .withExpiresAt(expirationDate())
            .sign(algorithm);
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant();
    }

}
