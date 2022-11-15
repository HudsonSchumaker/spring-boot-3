package br.com.schumaker.order.service;

import br.com.schumaker.order.model.OrderRepository;
import br.com.schumaker.order.model.Status;
import br.com.schumaker.order.model.dto.OrderDTO;
import br.com.schumaker.order.model.entity.Order;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getAll() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO getById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDTO.class);
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setDateTime(LocalDateTime.now());
        order.setStatus(Status.ACCOMPLISHED);
        order.getItems().forEach(item -> item.setOrder(order));
        var saved = repository.save(order);

        return modelMapper.map(order, OrderDTO.class);
    }

    @Transactional
    public OrderDTO updateStatus(Long id, Status dto) {
        var order = repository.byIdWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(dto);
        repository.updateStatus(dto, order);
        return modelMapper.map(order, OrderDTO.class);
    }

    @Transactional
    public void approvePayment(Long id) {
        var order = repository.byIdWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.PAID);
        repository.updateStatus(Status.PAID, order);
    }
}
