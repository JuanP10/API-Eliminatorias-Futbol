package com.example.eliminatoriasapi.repositories;

import com.example.eliminatoriasapi.models.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoRespository extends JpaRepository<Resultado, Long> {
}
