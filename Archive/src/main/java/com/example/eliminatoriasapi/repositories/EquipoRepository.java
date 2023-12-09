package com.example.eliminatoriasapi.repositories;

import com.example.eliminatoriasapi.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    Optional<Equipo> findByNombreEquipo (String nombre);
}
