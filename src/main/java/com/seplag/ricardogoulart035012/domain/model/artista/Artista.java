package com.seplag.ricardogoulart035012.domain.model.artista;

import com.seplag.ricardogoulart035012.domain.model.album.Album;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "artista",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Album> albuns = new ArrayList<>();

    public Artista() {}

    public Artista(String nome){
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }
}
