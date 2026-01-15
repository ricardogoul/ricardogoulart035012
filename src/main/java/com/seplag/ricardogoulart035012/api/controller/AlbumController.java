package com.seplag.ricardogoulart035012.api.controller;

import com.seplag.ricardogoulart035012.domain.model.album.Album;
import com.seplag.ricardogoulart035012.domain.service.AlbumService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Album>> listar() {
        return ResponseEntity.ok(albumService.listarTodos());
    }

    @PostMapping("/artista/{artistaId}")
    public ResponseEntity<Album> criar(
            @PathVariable Long artistaId,
            @RequestBody Album album) {
        Album albumSalvo = albumService.salvar(artistaId, album);
        return ResponseEntity.ok(albumSalvo);
    }
}
