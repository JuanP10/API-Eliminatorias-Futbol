package com.example.eliminatoriasapi.Controllers;
import com.example.eliminatoriasapi.Dtos.EquipoDto;
import com.example.eliminatoriasapi.Services.EquipoService;
import com.example.eliminatoriasapi.models.Equipo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/equipos")
@CrossOrigin(origins = "http://localhost:3000")

public class EquipoController {
    private final EquipoService equipoService;

    @PostMapping("/crear")
    public ResponseEntity<EquipoDto> create(@RequestBody @Validated Equipo equipo) {
        EquipoDto equipoDtoCreated = equipoService.create(equipo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(equipoDtoCreated.getIdEquipo())
                .toUri();
        return ResponseEntity.created(location).body(equipoDtoCreated);
    }

    @GetMapping
    public ResponseEntity<?> Listar(@RequestParam(required = false) String name){
        if (name != null){
            Optional<EquipoDto> team = equipoService.getEquipo(name);
            if (team.isPresent()){
                return new ResponseEntity<>(team.get(), HttpStatus.OK);
            }else
                return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(equipoService.getEquipos(), HttpStatus.OK);
    }

    @GetMapping ("buscar/{id}")
    public ResponseEntity<EquipoDto> BuscarPorId(@PathVariable Long id){
        Optional<EquipoDto> equipoDtoOptional = equipoService.findPorId(id);
        if (equipoDtoOptional.isPresent())
            return ResponseEntity.ok().body(equipoDtoOptional.get());
        return ResponseEntity.notFound().build();
    }



    @PutMapping("actualizar/{id}")
    public ResponseEntity<EquipoDto> update(@PathVariable Long id, @RequestBody @Validated Equipo equipo) {
        Optional<EquipoDto> equipoDtoUpdated = equipoService.UpdateEquipoPorId(id, equipo);
        if (equipoDtoUpdated != null)
            return ResponseEntity.ok().body(equipoDtoUpdated.get());
        EquipoDto equipoDtoCreated = equipoService.create(equipo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(equipoDtoCreated.getIdEquipo())
                .toUri();
        return ResponseEntity.created(location).body(equipoDtoCreated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Equipo> borrar (@PathVariable Long id){
        equipoService.borrar(id);
        return ResponseEntity.noContent().build();
    }

}
