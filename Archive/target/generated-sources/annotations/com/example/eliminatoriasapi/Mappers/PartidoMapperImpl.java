package com.example.eliminatoriasapi.Mappers;

import com.example.eliminatoriasapi.Dtos.PartidoDto;
import com.example.eliminatoriasapi.Dtos.ResultadoDto;
import com.example.eliminatoriasapi.models.Equipo;
import com.example.eliminatoriasapi.models.Partido;
import com.example.eliminatoriasapi.models.Resultado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-23T22:12:04-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PartidoMapperImpl implements PartidoMapper {

    @Override
    public PartidoDto partidoToPartidoDto(Partido partido) {
        if ( partido == null ) {
            return null;
        }

        PartidoDto partidoDto = new PartidoDto();

        partidoDto.setIdEquipoLocal( partidoEquipoLocalIdEquipo( partido ) );
        partidoDto.setIdEquipoVisitante( partidoEquipoVisitanteIdEquipo( partido ) );
        partidoDto.setIdPartido( partido.getIdPartido() );
        partidoDto.setFechaPartido( partido.getFechaPartido() );
        partidoDto.setEstadio( partido.getEstadio() );
        partidoDto.setArbitro( partido.getArbitro() );
        partidoDto.setMarcador( resultadoToResultadoDto( partido.getMarcador() ) );

        return partidoDto;
    }

    @Override
    public Partido partidoDtoToPartido(PartidoDto partidoDto) {
        if ( partidoDto == null ) {
            return null;
        }

        Partido partido = new Partido();

        partido.setEquipoLocal( partidoDtoToEquipo( partidoDto ) );
        partido.setEquipoVisitante( partidoDtoToEquipo1( partidoDto ) );
        partido.setIdPartido( partidoDto.getIdPartido() );
        partido.setFechaPartido( partidoDto.getFechaPartido() );
        partido.setEstadio( partidoDto.getEstadio() );
        partido.setArbitro( partidoDto.getArbitro() );
        partido.setMarcador( resultadoDtoToResultado( partidoDto.getMarcador() ) );

        return partido;
    }

    private Long partidoEquipoLocalIdEquipo(Partido partido) {
        if ( partido == null ) {
            return null;
        }
        Equipo equipoLocal = partido.getEquipoLocal();
        if ( equipoLocal == null ) {
            return null;
        }
        Long idEquipo = equipoLocal.getIdEquipo();
        if ( idEquipo == null ) {
            return null;
        }
        return idEquipo;
    }

    private Long partidoEquipoVisitanteIdEquipo(Partido partido) {
        if ( partido == null ) {
            return null;
        }
        Equipo equipoVisitante = partido.getEquipoVisitante();
        if ( equipoVisitante == null ) {
            return null;
        }
        Long idEquipo = equipoVisitante.getIdEquipo();
        if ( idEquipo == null ) {
            return null;
        }
        return idEquipo;
    }

    protected ResultadoDto resultadoToResultadoDto(Resultado resultado) {
        if ( resultado == null ) {
            return null;
        }

        ResultadoDto resultadoDto = new ResultadoDto();

        resultadoDto.setIdResultado( resultado.getIdResultado() );
        resultadoDto.setGolLocal( resultado.getGolLocal() );
        resultadoDto.setGolVisitante( resultado.getGolVisitante() );
        resultadoDto.setTarjetasAmarillas( resultado.getTarjetasAmarillas() );
        resultadoDto.setTarjetasRojas( resultado.getTarjetasRojas() );

        return resultadoDto;
    }

    protected Equipo partidoDtoToEquipo(PartidoDto partidoDto) {
        if ( partidoDto == null ) {
            return null;
        }

        Equipo equipo = new Equipo();

        equipo.setIdEquipo( partidoDto.getIdEquipoLocal() );

        return equipo;
    }

    protected Equipo partidoDtoToEquipo1(PartidoDto partidoDto) {
        if ( partidoDto == null ) {
            return null;
        }

        Equipo equipo = new Equipo();

        equipo.setIdEquipo( partidoDto.getIdEquipoVisitante() );

        return equipo;
    }

    protected Resultado resultadoDtoToResultado(ResultadoDto resultadoDto) {
        if ( resultadoDto == null ) {
            return null;
        }

        Resultado resultado = new Resultado();

        resultado.setIdResultado( resultadoDto.getIdResultado() );
        resultado.setGolLocal( resultadoDto.getGolLocal() );
        resultado.setGolVisitante( resultadoDto.getGolVisitante() );
        resultado.setTarjetasAmarillas( resultadoDto.getTarjetasAmarillas() );
        resultado.setTarjetasRojas( resultadoDto.getTarjetasRojas() );

        return resultado;
    }
}
