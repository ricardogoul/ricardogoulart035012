package com.seplag.ricardogoulart035012.domain.service;

import com.seplag.ricardogoulart035012.domain.model.artista.Artista;
import com.seplag.ricardogoulart035012.dto.request.ArtistaRequestDTO;
import com.seplag.ricardogoulart035012.dto.response.ArtistaResponseDTO;
import com.seplag.ricardogoulart035012.exception.RecursoNaoEncontradoException;
import com.seplag.ricardogoulart035012.infra.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<ArtistaResponseDTO> listarTodos(){
        return artistaRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public ArtistaResponseDTO buscaPorId(Long id){
        Artista artista = buscarArtistaPorId(id);
        return mapToResponse(artista);
    }

    public ArtistaResponseDTO salvar(ArtistaRequestDTO artistaDTO){
        Artista artista = new Artista();
        artista.setNome(artistaDTO.getNome());
        artista = artistaRepository.save(artista);
        return mapToResponse(artista);
    }

    public ArtistaResponseDTO atualizar(Long id, ArtistaRequestDTO artistaDTO){
        Artista artista = buscarArtistaPorId(id);
        artista.setNome(artistaDTO.getNome());
        artista = artistaRepository.save(artista);
        return mapToResponse(artista);
    }

    public void excluir(Long id){
        Artista artista = buscarArtistaPorId(id);
        artistaRepository.delete(artista);
    }

    private Artista buscarArtistaPorId(Long id){
        return artistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Artista não encontrado."
                ));
    }

    private ArtistaResponseDTO mapToResponse(Artista artista) {
        return new ArtistaResponseDTO(
                artista.getId(),
                artista.getNome(),
                artista.getAlbuns().size()
        );
    }
}
