package br.com.schumaker.order.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/*
    @author Hudson Schumaker
 */

@Getter
@Setter
@ToString
public class PaymentDTO {
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private String status;
    private Long orderId;
    private Long paymentMethod;
    private String log;
}
