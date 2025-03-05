package com.fawry.movieapp.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final long JWT_EXPIRATION_MS = 86400000; // 1 day expiration

    private static final String JWT_SECRET = "my_very_secure_secret_key__my_very_secure_secret_key__my_very_secure_secret_key_" +
            "my_very_secure_secret_key__my_very_secure_secret_key__my_very_secure_secret_key_my_very_secure_secret_key_" +
            "my_very_secure_secret_key__my_very_secure_secret_key__my_very_secure_secret_key_my_very_secure_secret_key_" +
            "my_very_secure_secret_key__my_very_secure_secret_key__my_very_secure_secret_key_my_very_secure_secret_key_" +
            "_my_very_secure_secret_key__my_very_secure_secret_key_my_very_secure_secret_key__my_very_secure_secret_key_";

    private final SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    // Generate token with the role included as a claim
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key, Jwts.SIG.HS512)
                .compact();
    }

    // Extract username from the token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    // Extract role from the token
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("role", String.class);
    }

    // Validate the token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}