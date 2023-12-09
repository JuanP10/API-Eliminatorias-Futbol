package com.example.eliminatoriasapi.Services.Implements;

import com.example.eliminatoriasapi.Dtos.PartidoDto;
import com.example.eliminatoriasapi.Mappers.PartidoMapper;
import com.example.eliminatoriasapi.Services.PartidoService;
import com.example.eliminatoriasapi.models.Partido;
import com.example.eliminatoriasapi.repositories.PartidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service

public class PartidoServiceImpl implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final PartidoMapper partidoMapper;


    @Override
    public PartidoDto guardar(PartidoDto partidoDto) {
        Partido partido = partidoMapper.partidoDtoToPartido(partidoDto);
        return partidoMapper.partidoToPartidoDto(partidoRepository.save(partido));
    }

    @Override
    public Optional<PartidoDto> buscarPartidoId(Long id) {
        Optional<Partido> partido = partidoRepository.findById(id);
        if(partido.isEmpty())
            throw new RuntimeException("No hay resultados para el partido con id: "+id);
        return partido.map(partidoMapper::partidoToPartidoDto);
    }

    //
    @Override
    public Optional<PartidoDto> UpdatePartidoPorId(Long id, PartidoDto partidoDto) {
        return partidoRepository.findById(id)
                .map(partido -> {
                    Partido partidoActualizado = partido.updatePartido(partidoMapper.partidoDtoToPartido(partidoDto));
                    partidoRepository.save(partidoActualizado);
                    return partidoMapper.partidoToPartidoDto(partidoActualizado);
                });
    }
    //


}
