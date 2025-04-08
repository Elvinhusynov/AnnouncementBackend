package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.entity.User;
import com.huseynov.announcementbackend.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService { //

    @Value("${jwt.expires-at}") // Tokenin bitmə müddətidi application.properties də yazılır.
    private Long expireTime;

    @Value("${jwt.secret-key}") // Tokeni generasiya etmək üçün.
    private String secretKey;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername()) // Tokenin kimə məxsus olduğunu göstərir.
                .setClaims(Map.of("roles", user.getRole()))
                .setIssuedAt(new Date())        // Tokenin haçan yarandiğidi.
                .setExpiration(new Date(System.currentTimeMillis() + expireTime)) // Tokenin bitmə müddəti
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isAccessTokenValid(String token) {
        return !isTokenExpired(token);
    }

    public String extractUsername(String token) { //Token-dən username-i götürmək üçündü
        return extractClaim(token, Claims::getSubject);
    }

    public Role extractRole(String token) {
        Claims claims = extractAllClaims(token);
        String role = claims.get("roles", String.class);
        return Role.valueOf(role);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

