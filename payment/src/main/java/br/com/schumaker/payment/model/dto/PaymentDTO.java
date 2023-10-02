package br.com.schumaker.payment.model.dto;

import br.com.schumaker.payment.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private Long id;

    @NotNull
    @Positive
    private BigDecimal value;

    @NotBlank
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
    private Long orderId;
    private Long paymentMethod;
}
