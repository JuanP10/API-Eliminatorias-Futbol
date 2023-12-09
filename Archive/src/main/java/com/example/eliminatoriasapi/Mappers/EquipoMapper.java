package com.example.eliminatoriasapi.Mappers;

import com.example.eliminatoriasapi.Dtos.EquipoDto;
import com.example.eliminatoriasapi.models.Equipo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(
        componentModel = "spring"
)
public interface EquipoMapper {
    EquipoDto equipoToEquipoDto(Equipo equipo);
    Equipo equipoDtoToEquipo(EquipoDto equipoDto);
}
