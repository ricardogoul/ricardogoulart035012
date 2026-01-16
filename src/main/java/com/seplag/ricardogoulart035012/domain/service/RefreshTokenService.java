package com.seplag.ricardogoulart035012.domain.service;

import com.seplag.ricardogoulart035012.domain.model.RefreshToken;
import com.seplag.ricardogoulart035012.domain.model.Usuario;
import com.seplag.ricardogoulart035012.infra.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.LocalDateTime;

@Service
public class RefreshTokenService {

    private static final long REFRESH_EXPIRATION_DAYS = 7;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken criarRefreshToken(Usuario usuario, String token){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsuario(usuario);
        refreshToken.setToken(token);
        refreshToken.setExpiracao(LocalDateTime.now().plusDays(REFRESH_EXPIRATION_DAYS));
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken validarRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido."));
        if(refreshToken.expirado()){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expirado.");
        }
        return refreshToken;
    }
}
