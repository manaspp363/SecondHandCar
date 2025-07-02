package com.app.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.auth0.jwt.JWT.require;

@Service
public class JWTService {
    @Value("${jwt.key}")
    private String algorithumKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry}")
    private int expiry;
    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws Exception {
        algorithm = Algorithm.HMAC256(algorithumKey);
    }

    public String genrateToken(String username) {
        return JWT.create().
                withClaim("username", username).
                withExpiresAt(new Date(System.currentTimeMillis() + expiry)).
                withIssuer(issuer).
                sign(algorithm);
    }

    public String getUsername(String token) {
        DecodedJWT decodedToken = require(algorithm)
                .withIssuer(issuer)
                .build().verify(token);
        return decodedToken.getClaim("username").asString();
    }
}

