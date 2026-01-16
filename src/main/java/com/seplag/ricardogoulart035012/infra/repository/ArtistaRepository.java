package com.seplag.ricardogoulart035012.infra.repository;

import com.seplag.ricardogoulart035012.domain.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

}
