package net.atos.propostaapp.service;

import lombok.AllArgsConstructor;
import net.atos.propostaapp.dto.PropostaRequestDto;
import net.atos.propostaapp.dto.PropostaResponseDto;
import net.atos.propostaapp.entity.Proposta;
import net.atos.propostaapp.mapper.PropostaMapper;
import net.atos.propostaapp.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PropostaService {
    private PropostaRepository propostaRepository;
    public PropostaResponseDto criar(PropostaRequestDto requestDto){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public List<PropostaResponseDto> obterProposta() {
        /* OUTRA POSSIVEL FORMA DE IMPLEMENTAR, A VARIAVEL PROPOSTAS SERIA UTILIZADO COMO PARAMENTRO DO RETURN
        Iterable<Proposta> propostas = propostaRepository.findAll();*/
        return PropostaMapper.INSTANCE.convertListEntityTOListDto(propostaRepository.findAll());
    }
}
