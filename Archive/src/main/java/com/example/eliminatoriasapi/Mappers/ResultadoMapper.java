package com.example.eliminatoriasapi.Mappers;

import com.example.eliminatoriasapi.Dtos.ResultadoDto;
import com.example.eliminatoriasapi.models.Resultado;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface ResultadoMapper {
    ResultadoDto resultadoToResultadoDto(Resultado resultado);
    Resultado resultadoDtoToResultado(ResultadoDto resultadoDto);

}

