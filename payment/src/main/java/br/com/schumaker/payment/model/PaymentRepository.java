package br.com.schumaker.payment.model;

import br.com.schumaker.payment.model.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
