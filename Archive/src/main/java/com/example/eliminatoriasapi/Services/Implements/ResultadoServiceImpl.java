package com.example.eliminatoriasapi.Services.Implements;

import com.example.eliminatoriasapi.Dtos.ResultadoDto;
import com.example.eliminatoriasapi.Mappers.ResultadoMapper;
import com.example.eliminatoriasapi.Services.ResultadoService;
import com.example.eliminatoriasapi.models.Resultado;
import com.example.eliminatoriasapi.repositories.ResultadoRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ResultadoServiceImpl implements ResultadoService {

    private final ResultadoRespository resultadoRespository;
    private final ResultadoMapper resultadoMapper;

    @Override
    public ResultadoDto createResultado(Resultado resultado) {
        Resultado resultCopy = resultCopy(resultado);
        return resultadoMapper.resultadoToResultadoDto(resultadoRespository.save(resultCopy));
    }


    @Override
    public Optional<ResultadoDto> updateResultado(Long id, Resultado resultado) {
        Optional<Resultado> optionalOldResult = resultadoRespository.findById(id);
        if (optionalOldResult.isPresent()) {
            Resultado oldResult = optionalOldResult.get();
            Resultado updateResult = oldResult.uptadeResultado(resultado);
            return Optional.of(resultadoMapper.resultadoToResultadoDto(resultadoRespository.save(updateResult)));
        } else {
            return Optional.empty();
        }
    }



    public Resultado resultCopy(Resultado resultado) {
        Resultado result1 = new Resultado();
        result1.setGolVisitante(resultado.getGolVisitante());
        result1.setGolLocal(resultado.getGolLocal());
        result1.setTarjetasRojas(resultado.getTarjetasRojas());
        result1.setTarjetasAmarillas(resultado.getTarjetasAmarillas());
        result1.setPartido(resultado.getPartido());
        return result1;
    }


}
