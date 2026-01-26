package com.seplag.ricardogoulart035012.dto.response;

import java.time.LocalDateTime;

public class ImagemResponseDTO {
    private Long id;
    private String nomeArquivo;
    private String tipo;
    private Long tamanho;
    private String path;
    private LocalDateTime dataUpload;
    private Long albumId;
    private String albumTitulo;

    public String getUrlTemporaria() {
        return urlTemporaria;
    }

    private String urlTemporaria;

    public ImagemResponseDTO(Long id, String nomeArquivo, String tipo, Long tamanho, String path, LocalDateTime dataUpload, Long albumId, String albumTitulo, String urlTemporaria) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.path = path;
        this.dataUpload = dataUpload;
        this.albumId = albumId;
        this.albumTitulo = albumTitulo;
        this.urlTemporaria = urlTemporaria;
    }

    public Long getId() {
        return id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public String getAlbumTitulo() {
        return albumTitulo;
    }
}
