package com.seplag.ricardogoulart035012.infra.repository;

import com.seplag.ricardogoulart035012.domain.model.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
