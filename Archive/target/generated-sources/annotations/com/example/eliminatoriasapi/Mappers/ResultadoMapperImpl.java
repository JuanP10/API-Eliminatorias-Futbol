package com.example.eliminatoriasapi.Mappers;

import com.example.eliminatoriasapi.Dtos.ResultadoDto;
import com.example.eliminatoriasapi.models.Resultado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-04T10:59:24-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ResultadoMapperImpl implements ResultadoMapper {

    @Override
    public ResultadoDto resultadoToResultadoDto(Resultado resultado) {
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

    @Override
    public Resultado resultadoDtoToResultado(ResultadoDto resultadoDto) {
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
