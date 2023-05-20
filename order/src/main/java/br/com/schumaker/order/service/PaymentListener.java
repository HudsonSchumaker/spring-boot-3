package br.com.schumaker.order.service;

import br.com.schumaker.order.model.dto.PaymentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @RabbitListener(queues = "payment.order")
    public void getMessage(@Payload PaymentDTO message) {
        System.out.println(message.toString());
    }
}
