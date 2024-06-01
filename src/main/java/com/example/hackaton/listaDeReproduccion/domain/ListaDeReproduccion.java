package com.example.hackaton.listaDeReproduccion.domain;

import com.example.hackaton.cancion.domain.Cancion;
import com.example.hackaton.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ListaDeReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD
    private Long listaReproduccionId;

    private String nombre;
    private Long idUser;
    private LocalDateTime fecha;
=======
    private Long idPlaylist;

    private String nombre;
    private Long idUser;
    private Date fecha;
>>>>>>> mateoAcabado

    @ManyToMany
    private List<Cancion> cancionList;

    @ManyToOne
    private User user;
}