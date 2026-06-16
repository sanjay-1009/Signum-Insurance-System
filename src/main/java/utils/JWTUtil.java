package utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTUtil {

    private static final String SECRET =
        "InsuranceClaimSystemSecretKey123456789";

    private static final Key key =
        Keys.hmacShaKeyFor(
            SECRET.getBytes()
        );

    public static String generateToken(
            String username,
            String role) {

        return Jwts.builder()

            .setSubject(username)

            .claim("role", role)

            .setIssuedAt(
                new Date()
            )

            .setExpiration(
                new Date(
                    System.currentTimeMillis()
                    + 86400000
                )
            )

            .signWith(
                key,
                SignatureAlgorithm.HS256
            )

            .compact();
    }
}