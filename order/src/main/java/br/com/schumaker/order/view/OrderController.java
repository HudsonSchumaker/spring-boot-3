package br.com.schumaker.order.view;

import br.com.schumaker.order.model.Status;
import br.com.schumaker.order.model.dto.OrderDTO;
import br.com.schumaker.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping()
    public List<OrderDTO> listAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> listById(@PathVariable @NotNull Long id) {
        OrderDTO dto = service.getById(id);
        return  ResponseEntity.ok(dto);
    }

    @GetMapping("/port")
    public String returnPort(@Value("${local.server.port}") String port){
        return String.format("used port %s", port);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO dto, UriComponentsBuilder uriBuilder) {
        OrderDTO order = service.createOrder(dto);
        URI address = uriBuilder.path("/orders/{id}").buildAndExpand(order.id()).toUri();
        return ResponseEntity.created(address).body(order);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody Status status){
        OrderDTO dto = service.updateStatus(id, status);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> doPayment(@PathVariable @NotNull Long id) {
        service.approvePayment(id);
        return ResponseEntity.noContent().build();

    }
}
