package com.example.hackaton.listaDeReproduccion.infrastructure;

import com.example.hackaton.listaDeReproduccion.domain.ListaDeReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaDeReproduccionRepository extends JpaRepository<ListaDeReproduccion, Long> {
    List<ListaDeReproduccion> findAllByUserId(Long userId);
}
