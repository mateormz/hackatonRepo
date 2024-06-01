package com.example.hackaton.cancion.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hackaton.cancion.domain.Cancion;
import com.example.hackaton.cancion.domain.CancionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CancionController {
    private final CancionService cancionService;

    @GetMapping
    public ResponseEntity<List<Cancion>> getAllCanciones() {
        return new ResponseEntity<>(cancionService.findAllCanciones(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cancion> createCancion(@RequestBody Cancion cancion) {
        return new ResponseEntity<>(cancionService.saveCancion(cancion), HttpStatus.CREATED);
    }

    @PutMapping("/canciones/{idSong}")
    public ResponseEntity<Cancion> updateCancion(@PathVariable Long idSong, @RequestBody Cancion cancion) {
        Optional<Cancion> updatedCancion = cancionService.updateCancion(idSong, cancion);
        if (updatedCancion.isPresent()) {
            return new ResponseEntity<>(updatedCancion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/canciones/{id}")
    public ResponseEntity<Void> deleteCancion(@PathVariable Long idSong) {
        cancionService.deleteCancionById(idSong);
        return new ResponseEntity<>(HttpStatus.OK);
    }//Esto sirve

}
