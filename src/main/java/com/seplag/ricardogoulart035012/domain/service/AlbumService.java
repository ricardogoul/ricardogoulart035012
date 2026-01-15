package com.seplag.ricardogoulart035012.domain.service;

import com.seplag.ricardogoulart035012.domain.model.album.Album;
import com.seplag.ricardogoulart035012.domain.model.artista.Artista;
import com.seplag.ricardogoulart035012.dto.request.AlbumRequestDTO;
import com.seplag.ricardogoulart035012.dto.response.AlbumResponseDTO;
import com.seplag.ricardogoulart035012.exception.RecursoNaoEncontradoException;
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

    public List<AlbumResponseDTO> listarTodos(){
        return albumRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public AlbumResponseDTO buscaPorId(Long id){
        Album album = buscaAlbumPorId(id);
        return mapToResponse(album);
    }

    public AlbumResponseDTO salvar(AlbumRequestDTO albumDTO){
        Artista artista = artistaRepository.findById(albumDTO.getArtistaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Artista não encontrado."
                ));
        Album album = new Album();
        album.setTitulo(albumDTO.getTitulo());
        album.setArtista(artista);
        album = albumRepository.save(album);
        return mapToResponse(album);
    }

    public AlbumResponseDTO atualizar(Long id, AlbumRequestDTO albumDTO){
        Album album = buscaAlbumPorId(id);
        album.setTitulo(albumDTO.getTitulo());
        album.setArtista(artistaRepository.findById(albumDTO.getArtistaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Artista não encontrado."
                )));
        album = albumRepository.save(album);
        return mapToResponse(album);
    }

    public void excluir(Long id){
        Album album = buscaAlbumPorId(id);
        albumRepository.delete(album);
    }

    private Album buscaAlbumPorId(Long id){
        return albumRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Album não encontrado."
                ));
    }

    private AlbumResponseDTO mapToResponse(Album album){
        return new AlbumResponseDTO(
                album.getId(),
                album.getTitulo(),
                album.getArtista().getId(),
                album.getArtista().getNome()
        );
    }
}
