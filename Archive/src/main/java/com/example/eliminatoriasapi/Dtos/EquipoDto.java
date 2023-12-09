package com.example.eliminatoriasapi.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EquipoDto {
    private Long idEquipo;
    private String nombreEquipo;
    private String urlBandera;
    private String directorTecnico;

}
