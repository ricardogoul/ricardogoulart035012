package com.seplag.ricardogoulart035012.infra.repository;

import com.seplag.ricardogoulart035012.domain.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    List<Imagem> findByAlbumId(Long albumId);
}

