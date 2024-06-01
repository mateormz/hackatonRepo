package com.example.hackaton.cancion.domain;

import com.example.hackaton.album.domain.Album;
import com.example.hackaton.artista.domain.Artista;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.hackaton.user.domain.User;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSong;

    private String titulo;

    @ManyToMany(mappedBy = "artistaID")
    List<Artista> artista;

    @JoinColumn(name = "idAlbum")
    @OneToOne(cascade = CascadeType.ALL)
    private Album album;

    private int duracion;

    @ManyToOne
    private User user;

    public int getDuracion() {
        return duracion;
    }

    public List<Artista> getArtista() {
        return artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public Album getAlbum() {
        return album;
    }
}