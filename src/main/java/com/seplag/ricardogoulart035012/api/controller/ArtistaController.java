package com.seplag.ricardogoulart035012.api.controller;

import com.seplag.ricardogoulart035012.domain.service.ArtistaService;
import com.seplag.ricardogoulart035012.dto.request.ArtistaRequestDTO;
import com.seplag.ricardogoulart035012.dto.response.ArtistaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistaResponseDTO> listar(){
        return artistaService.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistaResponseDTO buscarPorId(@PathVariable Long id){
        return artistaService.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistaResponseDTO criar(@Valid @RequestBody ArtistaRequestDTO artistaDTO){
        return artistaService.salvar(artistaDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistaResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ArtistaRequestDTO artistaDTO) {
        return artistaService.atualizar(id, artistaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        artistaService.excluir(id);
    }
}
