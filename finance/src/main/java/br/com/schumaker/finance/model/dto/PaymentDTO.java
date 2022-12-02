package br.com.schumaker.finance.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PaymentDTO {
    private BigDecimal value;
    private String name;
    private String number;
    private String code;
    private String status;
    private Long orderId;
    private Long paymentMethod;
}