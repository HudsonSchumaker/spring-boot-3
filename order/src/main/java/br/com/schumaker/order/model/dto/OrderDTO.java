package br.com.schumaker.order.model.dto;

import br.com.schumaker.order.model.Status;
import br.com.schumaker.order.model.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

/*
    @author Hudson Schumaker
 */

public record OrderDTO(Long id, LocalDateTime dateTime, Status status, List<OrderItem> items) {
}
