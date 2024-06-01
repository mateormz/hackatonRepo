package com.example.hackaton.listaDeReproduccion.domain;

import com.example.hackaton.cancion.domain.Cancion;
import com.example.hackaton.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class ListaDeReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPlaylist;

    private String nombre;
    private Long idUser;
    private Date fecha;

    @ManyToMany
    private List<Cancion> cancionList;

    @ManyToOne
    private User user;
}