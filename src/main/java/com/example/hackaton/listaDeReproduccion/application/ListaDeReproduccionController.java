package com.example.hackaton.listaDeReproduccion.application;

import com.example.hackaton.eventos.ListaCreadaEvent;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ListaDeReproduccion")
@RequiredArgsConstructor
public class ListaDeReproduccionController {
    private final ListaCr;

    @GetMapping("/{playlistId}")
    public ResponseEntity<String> getPlaylist(@PathVariable int playlistId) {
        return ResponseEntity.ok(listaDeReproduccionService.getPlaylist(playlistId));
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<ListaDeReproduccion> updatePlaylist(@PathVariable int playlistId, @RequestBody ListaDeReproduccion updatedPlaylist) {
        return ResponseEntity.ok(listaDeReproduccionService.updatePlaylist(playlistId, updatedPlaylist));
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable int playlistId) {
        listaDeReproduccionService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }
}
