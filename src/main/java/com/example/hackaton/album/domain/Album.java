package com.example.hackaton.album.domain;

import com.example.hackaton.cancion.domain.Cancion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idAlbum;

    private String nombre;

    private Date fechaDeLanzamiento;

    @OneToMany
    private List<Cancion> canciones;
}