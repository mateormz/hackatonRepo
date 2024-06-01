package com.example.hackaton.cancion.domain;

import com.example.hackaton.cancion.infrastructure.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    private CancionRepository cancionRepository;

    public List<Cancion> findAllCanciones() {
        return cancionRepository.findAll();
    }

    public Cancion saveCancion(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public Optional<Cancion> updateCancion(Long idSong, Cancion cancion) {
        Optional<Cancion> cancionOptional = cancionRepository.findById(idSong);
        if (cancionOptional.isPresent()) {
            Cancion cancionToUpdate = cancionOptional.get();
            cancionToUpdate.setTitulo(cancion.getTitulo());
            cancionToUpdate.setArtista(cancion.getArtista());
            cancionToUpdate.setAlbum(cancion.getAlbum());
            cancionToUpdate.setDuracion(cancion.getDuracion());
            return Optional.of(cancionRepository.save(cancionToUpdate));
        }
        return Optional.empty();
    }

    public void deleteCancionById(Long idSong) {//Esto sirve
        cancionRepository.deleteById(idSong);
    }
}