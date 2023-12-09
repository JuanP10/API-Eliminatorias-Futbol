package com.example.eliminatoriasapi.Mappers;

import com.example.eliminatoriasapi.Dtos.PartidoDto;
import com.example.eliminatoriasapi.models.Partido;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface PartidoMapper {
    @Mappings({
            @Mapping(source = "equipoLocal.idEquipo", target = "idEquipoLocal"),
            @Mapping(source = "equipoVisitante.idEquipo", target = "idEquipoVisitante")
    })
    PartidoDto partidoToPartidoDto(Partido partido);
    @InheritInverseConfiguration
    Partido partidoDtoToPartido(PartidoDto partidoDto);
}

