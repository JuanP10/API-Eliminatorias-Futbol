package com.example.eliminatoriasapi.Services;


import com.example.eliminatoriasapi.Dtos.EquipoDto;
import com.example.eliminatoriasapi.models.Equipo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    EquipoDto create(Equipo equipo);
    List<EquipoDto> getEquipos();
    Optional<EquipoDto> getEquipo(String nombre);
    Optional<EquipoDto> UpdateEquipoPorId(Long id, Equipo equipo);
    Optional<EquipoDto> findPorId(Long id);
    ResponseEntity<String> borrar(Long id);
}

