package com.seplag.ricardogoulart035012.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private Long expiration;

    @Value("${security.jwt.refresh-expiration}")
    private Long refreshExpiration;

    public String gerarAccessToken(String username) {
        return gerarToken(username, expiration);
    }

    public String gerarRefreshToken(String username) {
        return gerarToken(username, refreshExpiration);
    }

    public String gerarToken(String username, long expiration){
        Date agora = new Date();
        Date expira = new Date(agora.getTime() + expiration);
        return Jwts.builder()
                .subject(username)
                .issuedAt(agora)
                .expiration(expira)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String validarTokenEObterUsuario(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
