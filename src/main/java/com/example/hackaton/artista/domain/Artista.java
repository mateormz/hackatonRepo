package com.example.hackaton.artista.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idArtist;

    @NotBlank
    private String nombre;
}