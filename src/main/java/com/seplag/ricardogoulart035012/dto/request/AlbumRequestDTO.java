package com.seplag.ricardogoulart035012.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlbumRequestDTO {

    @NotBlank(message = "O título do album é obrigatório.")
    @Size(min = 1, max = 100, message = "O título do album deve ter entre 1 e 100 caracteres.")
    private String titulo;

    @NotNull(message = "o Id do artista é obrigatório.")
    private Long artistaId;

    public String getTitulo() {
        return titulo;
    }

    public Long getArtistaId() {
        return artistaId;
    }
}
