package br.com.schumaker.payment.model.dto;

import br.com.schumaker.payment.model.Status;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
    private Long orderId;
    private Long paymentMethod;
}
