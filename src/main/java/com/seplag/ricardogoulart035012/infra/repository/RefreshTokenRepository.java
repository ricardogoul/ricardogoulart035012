package com.seplag.ricardogoulart035012.infra.repository;

import com.seplag.ricardogoulart035012.domain.model.RefreshToken;
import com.seplag.ricardogoulart035012.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUsuario(Usuario usuario);
}
