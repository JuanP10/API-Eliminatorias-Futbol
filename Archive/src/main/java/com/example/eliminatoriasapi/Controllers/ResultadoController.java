package com.example.eliminatoriasapi.Controllers;
import com.example.eliminatoriasapi.Dtos.ResultadoDto;
import com.example.eliminatoriasapi.Services.ResultadoService;
import com.example.eliminatoriasapi.models.Resultado;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/resultados")
@CrossOrigin(origins = "http://localhost:3000")

public class ResultadoController {
    private final ResultadoService resultadoService;

    @PostMapping ("/crear")
    public ResponseEntity<ResultadoDto> createResultado (@RequestBody @Validated Resultado resultadoDto){
        ResultadoDto resultadoDtoGuardar = resultadoService.createResultado(resultadoDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idResultado}")
                .buildAndExpand(resultadoDtoGuardar.getIdResultado())
                .toUri();
        return ResponseEntity.created(location).body(resultadoDtoGuardar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultadoDto> updateResultado(@PathVariable Long id, @RequestBody @Validated Resultado resultado) {
        Optional<ResultadoDto> updatedResultadoOptional = resultadoService.updateResultado(id, resultado);
        if (updatedResultadoOptional.isPresent()) {
            return ResponseEntity.ok().body(updatedResultadoOptional.get());
        } else {
            ResultadoDto createdResultado = resultadoService.createResultado(resultado);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idResultado}")
                    .buildAndExpand(createdResultado.getIdResultado())
                    .toUri();
            return ResponseEntity.created(location).body(createdResultado);
        }
    }

}
