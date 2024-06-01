package com.example.hackaton.eventos;

import com.example.hackaton.configuration.EmailService;
import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackaton.listaDeReproduccion.infrastructure.ListaDeReproduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListaCreadaListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    ListaDeReproduccionRepository listaDeReproduccionRepository;

    @EventListener
    @Async
    public void handleListaReproduccionCreated(ListaCreadaEvent event){
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(event.getListaDeReproduccion().getIdPlaylist()).orElse(null);
        String message = "LISTA DE REPRODUCCION CREADA " + listaDeReproduccion.getNombre();
    }
}
