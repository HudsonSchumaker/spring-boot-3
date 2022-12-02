package br.com.schumaker.payment.view;

import br.com.schumaker.payment.model.Status;
import br.com.schumaker.payment.model.dto.PaymentDTO;
import br.com.schumaker.payment.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public Page<PaymentDTO> list(@PageableDefault(size = 10) Pageable pagination) {
        return service.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> details(@PathVariable @NotNull Long id) {
        PaymentDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody @Valid PaymentDTO dto, UriComponentsBuilder uriBuilder) {
        PaymentDTO payment = service.create(dto);
        URI address = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();

        return ResponseEntity.created(address).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO dto) {
        PaymentDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDTO> delete(@PathVariable @NotNull Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirm")
    @CircuitBreaker(name = "updateOrder", fallbackMethod = "paymentConfirmedWithoutIntegration")
    public void confirmPayment(@PathVariable @NotNull Long id){
        service.confirm(id);
    }

    public void paymentConfirmedWithoutIntegration(Long id, Exception e){
        service.updateStatus(id);
    }
}
