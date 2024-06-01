package com.example.hackaton.listaDeReproduccion.application;

import com.example.hackaton.cancion.domain.CancionDto;
import com.example.hackaton.cancion.infrastructure.CancionRepository;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccionDto;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccionService;
import com.example.hackaton.listaDeReproduccion.infrastructure.ListaDeReproduccionRepository;
import com.example.hackaton.user.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ListaDeReproduccion")
@RequiredArgsConstructor
public class ListaDeReproduccionController {

    @Autowired
    private ListaDeReproduccionService listaDeReproduccionService;

    // Crear una nueva lista de reproducción
    @PostMapping("/users/{user_id}/playlists")
    public ResponseEntity<ListaDeReproduccion> createPlaylist(@PathVariable int user_id , @RequestBody ListaDeReproduccion listaDeReproduccion) {
        ListaDeReproduccion createdPlaylist = listaDeReproduccionService.createListaDeReproduccion(user_id,listaDeReproduccion);
        return ResponseEntity.ok(createdPlaylist);
    }

    // Actualizar una lista de reproducción
    @PutMapping("/{id}")
    public ResponseEntity<ListaDeReproduccion> updatePlaylist(@PathVariable int id, @RequestBody ListaDeReproduccionDto listaDeReproduccionDto) {
        listaDeReproduccionDto.setIdPlaylist(id);
        ListaDeReproduccion updatedPlaylist = listaDeReproduccionService.updateListaReproduccion(listaDeReproduccionDto);
        return ResponseEntity.ok(updatedPlaylist);
    }

    // Añadir una canción a la lista de reproducción
    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> addSongToPlaylist(@PathVariable int playlistId, @PathVariable int songId) {
        listaDeReproduccionService.añadirCancion(playlistId, songId);
        return ResponseEntity.ok().build();
    }

    // Eliminar una canción de la lista de reproducción
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> removeSongFromPlaylist(@PathVariable int playlistId, @PathVariable int songId) {
        listaDeReproduccionService.eliminarCancion(playlistId, songId);
        return ResponseEntity.ok().build();
    }

    // Eliminar una lista de reproducción
    @DeleteMapping("/{ListaDeReproduccionId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable int ListaDeReproduccionId) {
        listaDeReproduccionService.eliminarListaDeReproducion(ListaDeReproduccionId);
        return ResponseEntity.ok().build();
    }

    // Buscar una canción en una lista de reproducción
    @GetMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<CancionDto> findSongInPlaylist(@PathVariable int playlistId, @PathVariable int songId) {
        CancionDto song = listaDeReproduccionService.findCancionfromPlaylist(playlistId, songId);
        return ResponseEntity.ok(song);
    }
}
