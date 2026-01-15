package com.seplag.ricardogoulart035012.domain.model.album;

import com.seplag.ricardogoulart035012.domain.model.artista.Artista;
import jakarta.persistence.*;

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

    public Album() {}

    public Album(String titulo, Artista artista) {
        this.titulo = titulo;
        this.artista = artista;
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

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
