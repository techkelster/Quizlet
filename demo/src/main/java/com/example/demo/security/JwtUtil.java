package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Directly define the JWT secret key here (instead of using app.properties)
    private static final String JWT_SECRET = "NzI1ZDJkNjAtNzJlNC00ZDIxLTkyZGUtMjk2YmE4N2M1MzY4"; // Your secret key

    private static final long EXPIRATION_MS = 86400000L; // Expiration time in milliseconds

    // Convert the secret from String to byte array and use it for signing
    private Key getSigningKey() {
        byte[] secretKeyBytes = JWT_SECRET.getBytes(); // Convert the secret string to a byte array
        return Keys.hmacShaKeyFor(secretKeyBytes); // Use the byte array to create the signing key
    }

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Validate the token's username and expiration
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Extract specific claim from the JWT token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
