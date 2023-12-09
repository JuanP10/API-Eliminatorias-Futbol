package com.example.eliminatoriasapi.Services;

import com.example.eliminatoriasapi.Dtos.EquipoDto;
import com.example.eliminatoriasapi.Dtos.PartidoDto;
import com.example.eliminatoriasapi.models.Equipo;
import com.example.eliminatoriasapi.models.Partido;

import java.util.List;
import java.util.Optional;

public interface PartidoService {
    PartidoDto guardar (PartidoDto partidoDto);
    Optional<PartidoDto> buscarPartidoId(Long id);

    Optional<PartidoDto> UpdatePartidoPorId (Long id, PartidoDto partidoDto);

}
