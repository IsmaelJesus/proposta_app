package net.atos.propostaapp.service;

import net.atos.propostaapp.dto.PropostaRequestDto;
import net.atos.propostaapp.dto.PropostaResponseDto;
import net.atos.propostaapp.entity.Proposta;
import net.atos.propostaapp.mapper.PropostaMapper;
import net.atos.propostaapp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {
    private PropostaRepository propostaRepository;
    private NotificacaoRabbitService notificacaoRabbitService;
    private String exchange;

    public PropostaService(PropostaRepository propostaRepository,
                           NotificacaoRabbitService notificacaoRabbitService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        notificarRabbitMQ(proposta);
        notificacaoRabbitService.notificar(proposta, exchange);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public void notificarRabbitMQ(Proposta proposta){
        try {
            notificacaoRabbitService.notificar(proposta, exchange);
        }catch(RuntimeException e){
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDto> obterProposta() {
        /* OUTRA POSSIVEL FORMA DE IMPLEMENTAR, A VARIAVEL PROPOSTAS SERIA UTILIZADO COMO PARAMENTRO DO RETURN
        Iterable<Proposta> propostas = propostaRepository.findAll();*/
        return PropostaMapper.INSTANCE.convertListEntityTOListDto(propostaRepository.findAll());
    }
}
