package com.seplag.ricardogoulart035012.dto.response;

public class ArtistaResponseDTO {

    private Long id;
    private String nome;
    private Integer quantidadeAlbuns;

    public ArtistaResponseDTO(Long id, String nome, Integer quantidadeAlbuns) {
        this.id = id;
        this.nome = nome;
        this.quantidadeAlbuns = quantidadeAlbuns;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidadeAlbuns() {
        return quantidadeAlbuns;
    }
}
