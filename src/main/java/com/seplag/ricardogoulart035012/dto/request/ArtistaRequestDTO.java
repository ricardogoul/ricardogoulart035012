package com.seplag.ricardogoulart035012.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArtistaRequestDTO {

    @NotBlank(message = "O nome do artista é obrigatório.")
    @Size(min = 1, max = 100, message = "O nome do artista deve ter entre 1 e 100 caracteres.")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
