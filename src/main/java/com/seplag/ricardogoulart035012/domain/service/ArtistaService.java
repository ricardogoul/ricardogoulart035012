package com.seplag.ricardogoulart035012.domain.service;

import com.seplag.ricardogoulart035012.domain.model.artista.Artista;
import com.seplag.ricardogoulart035012.infra.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<Artista> listarTodos(){
        return artistaRepository.findAll();
    }

    public Optional<Artista> buscaPorId(Long id){
        return artistaRepository.findById(id);
    }

    public Artista salvar(Artista artista){
        return artistaRepository.save(artista);
    }

    public void excluir(Long id){
        artistaRepository.deleteById(id);
    }
}
