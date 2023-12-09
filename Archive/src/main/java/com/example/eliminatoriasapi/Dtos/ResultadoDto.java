package com.example.eliminatoriasapi.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ResultadoDto {
    private Long idResultado;
    private Integer golLocal;
    private Integer golVisitante;
    private Integer tarjetasAmarillas;
    private Integer tarjetasRojas;
}
