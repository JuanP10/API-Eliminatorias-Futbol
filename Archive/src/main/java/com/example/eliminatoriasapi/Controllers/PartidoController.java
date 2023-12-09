package com.example.eliminatoriasapi.Controllers;

import com.example.eliminatoriasapi.Dtos.PartidoDto;
import com.example.eliminatoriasapi.Services.PartidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/partidos")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class PartidoController {
    private final PartidoService partidoService;

    @PostMapping("/crear")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PartidoDto> createPartido(@RequestBody @Validated PartidoDto partidoDto) {
        PartidoDto partidoDtoGuardar = partidoService.guardar(partidoDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idPartido}")
                .buildAndExpand(partidoDtoGuardar.getIdPartido())
                .toUri();
        return ResponseEntity.created(location).body(partidoDtoGuardar);
    }


    @PatchMapping("actualizar/{id}")
    public ResponseEntity<PartidoDto> update(@PathVariable Long id, @RequestBody @Validated PartidoDto partido) {
        Optional<PartidoDto> partidoDtoOptional = partidoService.UpdatePartidoPorId(id, partido);

        if (partidoDtoOptional.isPresent()) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idPartido}")
                    .buildAndExpand(partidoDtoOptional.get().getIdPartido())
                    .toUri();
            return ResponseEntity.created(location).body(partidoDtoOptional.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<PartidoDto> getById(@PathVariable Long id ) {
        Optional<PartidoDto> partidoDtoOptional = partidoService.buscarPartidoId(id);
        if (partidoDtoOptional.isPresent())
            return ResponseEntity.ok().body(partidoDtoOptional.get());
        else
            return ResponseEntity.notFound().build();
    }
}
