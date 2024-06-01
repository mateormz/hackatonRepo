package com.example.hackaton.listaDeReproduccion.domain;

import com.example.hackaton.cancion.domain.Cancion;
import com.example.hackaton.cancion.domain.CancionDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ListaDeReproduccionDto {
    Long idPlaylist;
    String nombre;
    Long idUser;
    List<CancionDto> cancionList;

}
