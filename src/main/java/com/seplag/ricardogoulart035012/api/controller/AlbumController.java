package com.seplag.ricardogoulart035012.api.controller;

import com.seplag.ricardogoulart035012.domain.service.AlbumService;
import com.seplag.ricardogoulart035012.dto.request.AlbumRequestDTO;
import com.seplag.ricardogoulart035012.dto.response.AlbumResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumResponseDTO> listar() {
        return albumService.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumResponseDTO buscarPorId(@PathVariable Long id){
        return albumService.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponseDTO criar(@Valid @RequestBody AlbumRequestDTO albumDTO) {
        return albumService.salvar(albumDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlbumRequestDTO albumDTO){
        return albumService.atualizar(id, albumDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        albumService.excluir(id);
    }
}
