package com.example.hackaton.listaDeReproduccion.application;

import com.example.hackaton.cancion.domain.CancionDto;
import com.example.hackaton.cancion.infrastructure.CancionRepository;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccionDto;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccionService;
import com.example.hackaton.listaDeReproduccion.infrastructure.ListaDeReproduccionRepository;
import com.example.hackaton.user.infrastructure.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ListaDeReproduccionController {

    @Autowired
    private ListaDeReproduccionService listaDeReproduccionService;
    @Autowired
    private ListaDeReproduccionRepository listaDeReproduccionRepository;

    // Crear una nueva lista de reproducción
    @PostMapping("/users/{user_id}/playlists")
    public ResponseEntity<ListaDeReproduccion> createPlaylist(@PathVariable Long user_id , @RequestBody ListaDeReproduccion listaDeReproduccion) throws Throwable {
        ListaDeReproduccion createdPlaylist = listaDeReproduccionService.createListaDeReproduccion(user_id,listaDeReproduccion);
        return ResponseEntity.ok(createdPlaylist);
    }

    @GetMapping("/playlist/{listaReproduccionId}")
    public ResponseEntity<ListaDeReproduccion> findPlaylist(@PathVariable Long listaReproduccionId){
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(listaReproduccionId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        return ResponseEntity.ok(listaDeReproduccion);
    }

    // Actualizar una lista de reproducción
    @PutMapping("/playlist/{listaReproduccionId}")
    public ResponseEntity<ListaDeReproduccion> updatePlaylist(@PathVariable Long id, @RequestBody ListaDeReproduccionDto listaDeReproduccionDto) {
        listaDeReproduccionDto.setIdPlaylist(id);
        ListaDeReproduccion updatedPlaylist = listaDeReproduccionService.updateListaReproduccion(listaDeReproduccionDto);
        return ResponseEntity.ok(updatedPlaylist);
    }

    // Añadir una canción a la lista de reproducción
    @PostMapping("/playlists/{listaReproduccionId}/songs}")
    public ResponseEntity<Void> addSongToPlaylist(@PathVariable Long listaReproduccionId, @RequestBody Long songId) {
        listaDeReproduccionService.añadirCancion(listaReproduccionId, songId);
        return ResponseEntity.ok().build();
    }

    // Eliminar una canción de la lista de reproducción
    @DeleteMapping("/playlists/{listaReproduccionId}/songs/{songId}")
    public ResponseEntity<Void> removeSongFromPlaylist(@PathVariable Long listaReproduccionId, @PathVariable Long songId) {
        listaDeReproduccionService.eliminarCancion(listaReproduccionId, songId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{playlist_id}/songs")
    public List<CancionDto> getAllCancionesDeListaReproduccion(@PathVariable("playlist_id") Long listaReproduccionId) {
        return listaDeReproduccionService.getAllCanciones(listaReproduccionId);
    }

    // Eliminar una lista de reproducción
    @DeleteMapping("/playlist/{listaReproduccionId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long listaReproduccionId) {
        listaDeReproduccionService.eliminarListaDeReproducion(listaReproduccionId);
        return ResponseEntity.ok().build();
    }

    // Buscar una canción en una lista de reproducción
    @GetMapping("/{listaReproduccionId}/songs/{songId}")
    public ResponseEntity<CancionDto> findSongInPlaylist(@PathVariable Long listaReproduccionId, @PathVariable Long songId) {
        CancionDto song = listaDeReproduccionService.findCancionfromPlaylist(listaReproduccionId, songId);
        return ResponseEntity.ok(song);
    }
    @GetMapping("/user/{userId}/playlists")
    public List<ListaDeReproduccionDto> getAllListasDeReproduccionByUserId(@PathVariable Long userId) {
        return listaDeReproduccionService.getAllListasDeReproduccionByUserId(userId);
    }
}
