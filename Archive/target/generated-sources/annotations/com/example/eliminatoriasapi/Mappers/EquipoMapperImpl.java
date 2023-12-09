package com.example.eliminatoriasapi.Mappers;

import com.example.eliminatoriasapi.Dtos.EquipoDto;
import com.example.eliminatoriasapi.models.Equipo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-23T22:12:04-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class EquipoMapperImpl implements EquipoMapper {

    @Override
    public EquipoDto equipoToEquipoDto(Equipo equipo) {
        if ( equipo == null ) {
            return null;
        }

        EquipoDto equipoDto = new EquipoDto();

        equipoDto.setIdEquipo( equipo.getIdEquipo() );
        equipoDto.setNombreEquipo( equipo.getNombreEquipo() );
        equipoDto.setUrlBandera( equipo.getUrlBandera() );
        equipoDto.setDirectorTecnico( equipo.getDirectorTecnico() );

        return equipoDto;
    }

    @Override
    public Equipo equipoDtoToEquipo(EquipoDto equipoDto) {
        if ( equipoDto == null ) {
            return null;
        }

        Equipo equipo = new Equipo();

        equipo.setIdEquipo( equipoDto.getIdEquipo() );
        equipo.setNombreEquipo( equipoDto.getNombreEquipo() );
        equipo.setUrlBandera( equipoDto.getUrlBandera() );
        equipo.setDirectorTecnico( equipoDto.getDirectorTecnico() );

        return equipo;
    }
}
