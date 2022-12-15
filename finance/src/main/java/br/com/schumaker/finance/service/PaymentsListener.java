package br.com.schumaker.finance.service;

import br.com.schumaker.finance.model.dto.PaymentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentsListener {

    @RabbitListener(queues = "finance.order")
    public void getMessage(@Payload PaymentDTO message) {
        System.out.println(message.getId());
        System.out.println(message.getNumber());

        if (message.getNumber().equals("000")) {
            throw new RuntimeException("simulating an error");
        }
        System.out.println(message.toString());
    }
}
