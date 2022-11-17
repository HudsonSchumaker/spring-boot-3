package br.com.schumaker.payment.service;

import br.com.schumaker.payment.http.OrderClient;
import br.com.schumaker.payment.model.PaymentRepository;
import br.com.schumaker.payment.model.Status;
import br.com.schumaker.payment.model.dto.PaymentDTO;
import br.com.schumaker.payment.model.entity.Payment;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderClient orderClient;


    public Page<PaymentDTO> getAll(Pageable pagination) {
        return repository
                .findAll(pagination)
                .map(p -> modelMapper.map(p, PaymentDTO.class));
    }

    public PaymentDTO getById(Long id) {
        Payment Payment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(Payment, PaymentDTO.class);
    }

    public PaymentDTO create(PaymentDTO dto) {
        Payment Payment = modelMapper.map(dto, Payment.class);
        Payment.setStatus(Status.CREATED);
        repository.save(Payment);

        return modelMapper.map(Payment, PaymentDTO.class);
    }

    public PaymentDTO update(Long id, PaymentDTO dto) {
        Payment Payment = modelMapper.map(dto, Payment.class);
        Payment.setId(id);
        Payment = repository.save(Payment);
        return modelMapper.map(Payment, PaymentDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void confirm(Long id){
        Optional<Payment> Payment = repository.findById(id);

        if (!Payment.isPresent()) {
            throw new EntityNotFoundException();
        }

        Payment.get().setStatus(Status.CONFIRMED);
        repository.save(Payment.get());
        orderClient.updatePayment(Payment.get().getOrderId());
    }

    public void updateStatus(Long id) {
        Optional<Payment> Payment = repository.findById(id);
        if (Payment.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Payment.get().setStatus(Status.CONFIRMED_WITHOUT_INTEGRATION);
        repository.save(Payment.get());
    }
}