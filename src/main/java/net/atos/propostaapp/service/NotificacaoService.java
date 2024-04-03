package net.atos.propostaapp.service;

import lombok.AllArgsConstructor;
import net.atos.propostaapp.dto.PropostaResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDto propostaResponseDto, String exchange){
        rabbitTemplate.convertAndSend(exchange, "", propostaResponseDto);
    }
}
