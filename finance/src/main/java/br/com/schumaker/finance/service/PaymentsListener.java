package br.com.schumaker.finance.service;

import br.com.schumaker.finance.model.dto.PaymentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentsListener {

    @RabbitListener(queues = "payment.done")
    public void getMessage(PaymentDTO message) {
        System.out.println(message.toString());
    }
}
