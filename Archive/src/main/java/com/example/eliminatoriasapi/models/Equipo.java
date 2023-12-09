package com.example.eliminatoriasapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;
    private String nombreEquipo;
    private String urlBandera;
    private String directorTecnico;
    @OneToMany(mappedBy = "equipoLocal")
    private Set<Partido> PartidosDeLocal;
    @OneToMany(mappedBy = "equipoVisitante")
    private Set<Partido> PartidosDeVisitante;

    public Equipo updateEquipo (Equipo equipo){
        return new Equipo(this.idEquipo, equipo.nombreEquipo, equipo.urlBandera,
                equipo.directorTecnico, equipo.PartidosDeLocal, equipo.PartidosDeVisitante);
    }

}
