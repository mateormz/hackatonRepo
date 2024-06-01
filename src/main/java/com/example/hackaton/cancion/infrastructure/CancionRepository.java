package com.example.hackaton.cancion.infrastructure;

import com.example.hackaton.cancion.domain.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
}
