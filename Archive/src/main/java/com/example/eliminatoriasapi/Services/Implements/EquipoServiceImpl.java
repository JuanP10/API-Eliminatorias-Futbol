package com.example.eliminatoriasapi.Services.Implements;

import com.example.eliminatoriasapi.Dtos.EquipoDto;
import com.example.eliminatoriasapi.Mappers.EquipoMapper;
import com.example.eliminatoriasapi.Services.EquipoService;
import com.example.eliminatoriasapi.models.Equipo;
import com.example.eliminatoriasapi.repositories.EquipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;
    private static final Logger log = LoggerFactory.getLogger(EquipoServiceImpl.class);

    @Override
    public EquipoDto create(Equipo equipo) {
        Equipo EquipoCopia = EquipoCopia(equipo);
        equipoRepository.save(EquipoCopia);
        return equipoMapper.equipoToEquipoDto(EquipoCopia);
    }
    public Equipo EquipoCopia(Equipo equipo) {
        Equipo team1 = new Equipo();
        team1.setIdEquipo(equipo.getIdEquipo());
        team1.setNombreEquipo(equipo.getNombreEquipo());
        team1.setUrlBandera(equipo.getUrlBandera());
        team1.setDirectorTecnico(equipo.getDirectorTecnico());
        team1.setPartidosDeVisitante(equipo.getPartidosDeVisitante());
        team1.setPartidosDeLocal(equipo.getPartidosDeLocal());
        return team1;
    }

    @Override
    public List<EquipoDto> getEquipos() {
        List<Equipo> equipos = equipoRepository.findAll();
        return equipos.stream()
                .map(equipoMapper::equipoToEquipoDto)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<EquipoDto> getEquipo(String nombre){
        Optional<Equipo> equipo = equipoRepository.findByNombreEquipo(nombre);
        if(equipo.isEmpty())
            throw new TeamNoFoundException("El equipo no se encuentra en la base de datos");
        return equipo.map(equipoMapper::equipoToEquipoDto);

    }

    //
    @Override
    public Optional<EquipoDto> UpdateEquipoPorId(Long id, Equipo equipo) {
        Optional<Equipo> equipoExiste = equipoRepository.findById(id);
        if (equipoExiste != null) {
            Equipo updatedEquipo = equipoExiste.get().updateEquipo(equipo);
            equipoRepository.save(updatedEquipo);
            return Optional.of(equipoMapper.equipoToEquipoDto(updatedEquipo));
        } else {
            EquipoDto updatedEquipoDto = equipoMapper.equipoToEquipoDto(equipoRepository.save(equipo));
            return Optional.ofNullable(updatedEquipoDto);
        }
    }
    //
    //
    @Override
    public Optional<EquipoDto> findPorId(Long id) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        if(equipoOptional.isPresent())
            return equipoOptional.map(equipoMapper::equipoToEquipoDto);
        throw new TeamNoFoundException("El equipo con la id "+id+ "  no se encuentra en la base de datos");
    }
    public class TeamNoFoundException extends RuntimeException {
        public TeamNoFoundException(String message) {
            super(message);
        }
    }
    //

    @Override
    public ResponseEntity<String> borrar(Long id) {
        try {
            equipoRepository.deleteById(id);
            log.info("Equipo con ID {} borrado correctamente.", id);
            return ResponseEntity.ok("Equipo borrado correctamente.");
        } catch (EmptyResultDataAccessException e) {
            log.warn("Intento de borrar un equipo inexistente con ID {}.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipo no encontrado para borrar.");
        } catch (Exception e) {
            log.error("Error al intentar borrar el equipo con ID {}.", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al intentar borrar el equipo.");
        }
    }
}
