package com.seplag.ricardogoulart035012.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens = new ArrayList<>();

    public Album() {}

    public Album(String titulo, Artista artista, List<Imagem> imagens) {
        this.titulo = titulo;
        this.artista = artista;
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }
}
