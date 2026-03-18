package com.ParkeoX.ParkeoX.services.payments.method;


import com.ParkeoX.ParkeoX.DTO.request.paymentsMethodDTO.PaymentsMethodDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.PaymentMethod;
import com.ParkeoX.ParkeoX.repository.paymentsMethod.PaymentsMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PaymentMehodService implements IPaymentmethodService{


    private final PaymentsMethodRepository repo;

    @Override
    public List<PaymentsMethodDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public PaymentsMethodDTO createPayment(PaymentsMethodDTO paymentDTO) {

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .name(paymentDTO.getName())
                .active(paymentDTO.getActive())
                .build();

        return  Mapper.toDTO(repo.save(paymentMethod));

    }

    @Override
    public PaymentsMethodDTO updatePayment(Long id, PaymentsMethodDTO paymentDTO) {
        // Search Payment method

        PaymentMethod paymentMethod = repo.findById(id).orElseThrow(()->new NotFoundException("PaymentMethod not found"));

        paymentMethod.setName(paymentDTO.getName());
        paymentMethod.setActive(paymentDTO.getActive());
        return Mapper.toDTO(repo.save(paymentMethod));


    }

    @Override
    public void deletePayment(Long id) {

        PaymentMethod paymentMethod = repo.findById(id).orElseThrow(()->new NotFoundException("PaymentMethod not found to delete"));
        repo.deleteById(id);

    }
}
