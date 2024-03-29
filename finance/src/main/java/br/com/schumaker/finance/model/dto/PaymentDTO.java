package br.com.schumaker.finance.model.dto;

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
    private String code;
}