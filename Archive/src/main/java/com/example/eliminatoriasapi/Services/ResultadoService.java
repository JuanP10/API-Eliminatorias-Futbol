package com.example.eliminatoriasapi.Services;

import com.example.eliminatoriasapi.Dtos.ResultadoDto;
import com.example.eliminatoriasapi.models.Resultado;

import java.util.Optional;

public interface ResultadoService {
    ResultadoDto createResultado(Resultado resultado);
    Optional<ResultadoDto> updateResultado(Long id, Resultado resultado);
}
