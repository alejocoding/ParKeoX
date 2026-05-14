package com.ParkeoX.ParkeoX.security.jwt;

import com.ParkeoX.ParkeoX.models.Company;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "bXlfc2VjcmV0X2tleV8xMjM0NTY3ODkwMTIzNDU2Nzg5MA==";

    public String generateToken(String nombre, String email, String rol, String estado, String company) {
        return Jwts.builder()
                .setSubject(email)
                .claim("nombre", nombre)
                .claim("rol", rol)
                .claim("estado", estado)
                .claim("company", company)
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
