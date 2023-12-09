package com.example.eliminatoriasapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Resultados")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Resultado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultado;
    private Integer golLocal;
    private Integer golVisitante;

    private Integer tarjetasAmarillas;
    private Integer tarjetasRojas;

    @OneToOne(mappedBy = "marcador")
    private Partido partido;

    public Resultado uptadeResultado (Resultado resultado){
        return new Resultado(resultado.idResultado, resultado.golLocal, resultado.golVisitante,
                resultado.tarjetasAmarillas,resultado.tarjetasRojas,resultado.partido);
    }

}
