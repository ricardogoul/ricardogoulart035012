package com.seplag.ricardogoulart035012.api.controller;

import com.seplag.ricardogoulart035012.domain.service.ImagemService;
import com.seplag.ricardogoulart035012.dto.response.ImagemResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/albuns/{albumId}/imagens")
public class ImagemController {

    private final ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ImagemResponseDTO> listarImagens(@PathVariable Long albumId) {
        return imagemService.listarPorAlbum(albumId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ImagemResponseDTO upload(
            @PathVariable Long albumId,
            @RequestParam("file") MultipartFile file) {
        return imagemService.salvar(albumId, file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        imagemService.deletarImagem(id);
    }
}
