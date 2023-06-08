package com.example.parcialbiblioteca.Security;

import com.example.parcialbiblioteca.entity.Usuario;
import com.example.parcialbiblioteca.repository.UsuarioRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-in-ms}")
    private int jwtExpiration;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        Usuario usuario = usuarioRepository.findByEmail(username).get();

        Map<String, Object> usuarioData = new HashMap<>();
        usuarioData.put("name", usuario.getNombre());
        usuarioData.put("lastName", usuario.getApellido());


        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .claim("Data", usuarioData)
                .claim("Roles", authentication.getAuthorities())
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
