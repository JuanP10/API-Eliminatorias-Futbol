package com.example.eliminatoriasapi.Dtos;

import com.example.eliminatoriasapi.models.Resultado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class PartidoDto {
    private Long idPartido;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaPartido;
    private String estadio;
    private String arbitro;
    private Long idEquipoLocal;
    private Long idEquipoVisitante;
    private ResultadoDto marcador;
}
