package com.seplag.ricardogoulart035012.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "imagens")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Long tamanho;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private LocalDateTime dataUpload;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public Imagem() {
    }

    public Imagem(String nomeArquivo, String tipo, Long tamanho, String path, LocalDateTime dataUpload, Album album) {
        this.nomeArquivo = nomeArquivo;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.path = path;
        this.dataUpload = dataUpload;
        this.album = album;
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

    public Album getAlbum() {
        return album;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
