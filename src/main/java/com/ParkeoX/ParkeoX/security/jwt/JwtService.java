package com.ParkeoX.ParkeoX.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "bXlfc2VjcmV0X2tleV8xMjM0NTY3ODkwMTIzNDU2Nzg5MA==";

    public String generateToken(String email, String rol, String estado) {
        return Jwts.builder()
                .setSubject(email)
                .claim("rol", rol)
                .claim("estado", estado)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
