package com.seplag.ricardogoulart035012.domain.service;

import com.seplag.ricardogoulart035012.domain.model.album.Album;
import com.seplag.ricardogoulart035012.domain.model.artista.Artista;
import com.seplag.ricardogoulart035012.infra.repository.AlbumRepository;
import com.seplag.ricardogoulart035012.infra.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistaRepository artistaRepository) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
    }

    public List<Album> listarTodos(){
        return albumRepository.findAll();
    }

    public Album salvar(Long artistaId, Album album){
        Artista artista = artistaRepository.findById(artistaId)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado."));

        album.setArtista(artista);
        return albumRepository.save(album);
    }
}
