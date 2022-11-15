package br.com.schumaker.payment.model.dto;

import br.com.schumaker.payment.model.Status;

import java.math.BigDecimal;

public record PaymentDTO(Long id,
                         BigDecimal value,
                         String name,
                         String number,
                         String expiration,
                         String code,
                         Status status,
                         Long orderId,
                         Long paymentMethod) {
}
