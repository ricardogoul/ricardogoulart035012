package com.seplag.ricardogoulart035012.api.controller;

import com.seplag.ricardogoulart035012.domain.model.artista.Artista;
import com.seplag.ricardogoulart035012.domain.service.ArtistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {
    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public ResponseEntity<List<Artista>> listar(){
        return ResponseEntity.ok(artistaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id){
        return artistaService.buscaPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Artista> criar(@RequestBody Artista artista){
        Artista artistaSalvo = artistaService.salvar(artista);
        return ResponseEntity.ok(artistaSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(
            @PathVariable Long id,
            @RequestBody Artista artista) {
        return artistaService.buscaPorId(id)
                .map(artistaSelecionado -> {artistaSelecionado.setNome(artista.getNome());
                return ResponseEntity.ok(artistaService.salvar(artistaSelecionado));})
                .orElse(ResponseEntity.notFound().build());
    }
}
