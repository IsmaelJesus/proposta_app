package net.atos.propostaapp.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitAdminEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public Queue criarFilaPropostaPendeteMsAnaliseCredito(){
        return QueueBuilder.durable("proposta-pendete.ms-analise-credito").build();
    }

    @Bean
    public Queue criarFilaPropostaPendeteMsNotificacao(){
        return QueueBuilder.durable("proposta-pendete.ms-notificacao").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsProposta(){
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsNotificacao(){
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    //Criação da exchange
    @Bean
    public FanoutExchange criarFanoutExchangePropostaPendete(){
        return ExchangeBuilder.fanoutExchange("proposta-pendente.ex").build();
    }

    @Bean
    public Binding criarBindingPropostaPendeteMSAnaliseCredito(){
        return BindingBuilder.bind(criarFilaPropostaPendeteMsAnaliseCredito())
                .to(criarFanoutExchangePropostaPendete());
    }

    @Bean
    public Binding criarBindingPropsotaPendeteMSNotificacao(){
        return BindingBuilder.bind(criarFilaPropostaPendeteMsNotificacao())
                .to(criarFanoutExchangePropostaPendete());
    }
}
