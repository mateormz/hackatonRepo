package com.example.hackaton.listaDeReproduccion.domain;

import com.example.hackaton.cancion.domain.Cancion;
import com.example.hackaton.cancion.domain.CancionDto;
import com.example.hackaton.cancion.infrastructure.CancionRepository;
import com.example.hackaton.eventos.ListaCreadaEvent;
import com.example.hackaton.user.domain.User;
import com.example.hackaton.user.infrastructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import com.example.hackaton.listaDeReproduccion.infrastructure.ListaDeReproduccionRepository;

import java.time.LocalDateTime;


@Service
public class ListaDeReproduccionService {

    @Autowired
    private ListaDeReproduccionRepository listaDeReproduccionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private ModelMapper mapper;


    //crear
    public ListaDeReproduccion createListaDeReproduccion(int userId, ListaDeReproduccion listaDeReproduccion) {
        ListaDeReproduccion newLista = new ListaDeReproduccion();
        newLista.setNombre(listaDeReproduccion.getNombre());
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        newLista.setUser(user);
        newLista.setFecha(LocalDateTime.now());

        applicationEventPublisher.publishEvent(new ListaCreadaEvent(this, user.getEmail(), newLista));

        return newLista;
    }

    public ListaDeReproduccion updateListaReproduccion(ListaDeReproduccionDto listaDeReproduccionDto){
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(listaDeReproduccionDto.getIdPlaylist()).orElseThrow(() -> new RuntimeException("Playlist not found"));
        listaDeReproduccion.setNombre(listaDeReproduccionDto.getNombre());
        listaDeReproduccionRepository.save(listaDeReproduccion);

        return listaDeReproduccion;
    }


    //modificar añadir cancion
    public void añadirCancion(int listaReproduccionId , int cancionId){
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(listaReproduccionId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        Cancion cancion = cancionRepository.findById(cancionId).orElseThrow(() -> new RuntimeException("Cancion not found"));
        listaDeReproduccion.getCancionList().add(cancion);
    }

    //modificar eliminar cancion
    public void eliminarCancion(int listaReproduccionId , int cancionId){
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(listaReproduccionId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        Cancion cancion = cancionRepository.findById(cancionId).orElseThrow(() -> new RuntimeException("Cancion not found"));
        listaDeReproduccion.getCancionList().remove(cancion);
    }

    
    //modificar eliminar playlist
    public void eliminarListaDeReproducion(int listaReproduccionId){
        ListaDeReproduccion eliminar = listaDeReproduccionRepository.findById(listaReproduccionId).orElseThrow(() -> new RuntimeException("Playlist not found"));;
        listaDeReproduccionRepository.delete(eliminar);
    }

    //buscarCancionDeLaPlaylist

    public CancionDto findCancionfromPlaylist(int listaReproduccionId, int cancionId) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(listaReproduccionId)
                .orElseThrow(() -> new RuntimeException("Lista de reproducción no encontrada"));

        Cancion cancion = listaDeReproduccion.getCancionList().stream()
                .filter(c -> c.getIdSong() == cancionId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Canción no encontrada en la lista de reproducción"));

        return convertToDto(cancion);
    }

    private CancionDto convertToDto(Cancion cancion) {
        return mapper.map(cancion, CancionDto.class);
    }

}
