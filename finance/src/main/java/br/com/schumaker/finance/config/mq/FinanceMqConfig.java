package br.com.schumaker.finance.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinanceMqConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue financeQueue() {
        return QueueBuilder
                .nonDurable("finance.order")
                .deadLetterExchange("finance.order-dlx")
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("payment.ex")
                .build();
    }

    @Bean
    public Binding bindOrderPayment() {
        return BindingBuilder
                .bind(financeQueue())
                .to(fanoutExchange());
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    // DLQ DLX

    @Bean
    public Queue dlqFinance() {
        return QueueBuilder
                .nonDurable("finance.order-dlq")
                .build();
    }

    @Bean
    public FanoutExchange dlxFinance() {
        return ExchangeBuilder
                .fanoutExchange("finance.order-dlx")
                .build();
    }

    @Bean
    public Binding bindFinanceDqlDlx() {
        return BindingBuilder
                .bind(dlqFinance())
                .to(dlxFinance());
    }
}
