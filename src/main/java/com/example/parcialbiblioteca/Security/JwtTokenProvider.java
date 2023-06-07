package com.example.parcialbiblioteca.Security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-in-ms}")
    private int jwtExpiration;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return token;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;

        } catch (SignatureException ex) {
            throw new SignatureException("Invalid JWT signature");
        }
        catch (MalformedJwtException ex) {
            throw new SignatureException("Invalid JWT token");
        }
        catch (ExpiredJwtException ex) {
            throw new SignatureException("Expired JWT token");
        }
        catch (UnsupportedJwtException ex) {
            throw new SignatureException("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex) {
            throw new SignatureException("JWT claims string is empty");
        }
    }
}
