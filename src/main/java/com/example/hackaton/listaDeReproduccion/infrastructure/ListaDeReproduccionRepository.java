package com.example.hackaton.listaDeReproduccion.infrastructure;

import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeReproduccionRepository extends JpaRepository<ListaDeReproduccion, Integer> {
}
