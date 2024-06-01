package com.example.hackaton.eventos;

import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccion;
import org.springframework.context.ApplicationEvent;

public class ListaCreadaEvent extends ApplicationEvent {
    private String email;
    private ListaDeReproduccion listaDeReproduccion;

    public ListaCreadaEvent(Object source, String email, ListaDeReproduccion ListaDeReproduccion) {
        super(source);
        this.email = email;
        this.listaDeReproduccion = ListaDeReproduccion;
    }

    public String getEmail() {return email;}
    public  ListaDeReproduccion getListaDeReproduccion() {return listaDeReproduccion;}
}
