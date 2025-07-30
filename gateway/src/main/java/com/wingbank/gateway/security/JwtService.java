package com.wingbank.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.keys.public}") // New property for base64 encoded public key
    private String jwtPublicKeyBase64;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private PublicKey getPublicKey() {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(jwtPublicKeyBase64);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Error loading public key", e);
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getPublicKey()) // Use verifyWith for Jwts.parser()
                .build()
                .parseSignedClaims(token) // Use parseSignedClaims
                .getPayload();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(getPublicKey()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) { // Catch more general Exception for security reasons on validation
            logger.error("JWT validation error: {}", e.getMessage());
        }
        return false;
    }

    /*public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: ", e);
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: ", e);
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: ", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: ", e);
        }
        return false;
    }*/
}