package com.example.eliminatoriasapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Partidos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Partido {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idPartido;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaPartido;
    private String estadio;
    private String arbitro;
    @ManyToOne
    @JoinColumn(name = "idEquipoLocal")
    private Equipo equipoLocal;
    @ManyToOne
    @JoinColumn(name = "idEquipoVisitante")
    private Equipo equipoVisitante;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "idResultado")
    private Resultado marcador;

    public Partido updatePartido (Partido partido){
        return new Partido(this.idPartido, partido.fechaPartido, partido.estadio, partido.arbitro,
                partido.equipoLocal, partido.equipoVisitante, partido.marcador);
    }

}
