package utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTUtil {

    private static final Key SECRET_KEY =
            Keys.hmacShaKeyFor(
            "InsuranceClaimSystemJWTSecretKey2026"
            .getBytes());

    public static String generateToken(
            String username,
            String role) {

        return Jwts.builder()

                .setSubject(username)

                .claim(
                        "role",
                        role)

                .setIssuedAt(
                        new Date())

                .setExpiration(
                        new Date(
                        System.currentTimeMillis()
                        + 86400000))

                .signWith(
                        SECRET_KEY)

                .compact();
    }

    public static Claims validateToken(
            String token) {

        return Jwts.parserBuilder()

                .setSigningKey(
                        SECRET_KEY)

                .build()

                .parseClaimsJws(
                        token)

                .getBody();
    }
}