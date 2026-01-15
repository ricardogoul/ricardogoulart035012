package com.seplag.ricardogoulart035012.dto.response;

public class AlbumResponseDTO {

    private Long id;
    private String titulo;
    private Long artistaId;
    private String nomeArtista;

    public AlbumResponseDTO(Long id, String titulo, Long artistaId, String nomeArtista) {
        this.id = id;
        this.titulo = titulo;
        this.artistaId = artistaId;
        this.nomeArtista = nomeArtista;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getArtistaId() {
        return artistaId;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }
}
