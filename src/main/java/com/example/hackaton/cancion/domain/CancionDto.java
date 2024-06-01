package com.example.hackaton.cancion.domain;

import com.example.hackaton.album.domain.Album;
import com.example.hackaton.artista.domain.Artista;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class CancionDto {

    int idSong;

    String titulo;

    List<Artista> artista;

    Album album;

    int duracion;
}
