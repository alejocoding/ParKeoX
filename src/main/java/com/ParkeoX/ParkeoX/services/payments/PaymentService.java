package com.ParkeoX.ParkeoX.services.payments;


import com.ParkeoX.ParkeoX.DTO.request.paymentsDTO.PaymentsDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.PaymentMethod;
import com.ParkeoX.ParkeoX.models.Payments;
import com.ParkeoX.ParkeoX.models.Tickets;
import com.ParkeoX.ParkeoX.repository.paymentsMethod.PaymentsMethodRepository;
import com.ParkeoX.ParkeoX.repository.paymentsRepository.PaymentsRepository;
import com.ParkeoX.ParkeoX.repository.ticketsRepository.TicketsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class PaymentService implements IPaymentService{


    private final PaymentsRepository repo;
    private final TicketsRepository ticketsRepo;
    private final PaymentsMethodRepository methodRepo;

    @Override
    public List<PaymentsDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public PaymentsDTO createPayment(PaymentsDTO paymentsDTO) {

        //Search Ticket

        Tickets ticket = ticketsRepo.findById(paymentsDTO.getTicket())
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        PaymentMethod method = methodRepo.findById(paymentsDTO.getPaymentMethod()).orElseThrow(() -> new NotFoundException("PaymentMethod not found"));

        Payments p = Payments.builder()
                .ticket(ticket)
                .PaymentMethod(method)
                .amount(paymentsDTO.getAmount())
                .build();

        return Mapper.toDTO(repo.save(p));
    }

    @Override
    public PaymentsDTO updatePayment(Long id, PaymentsDTO paymentsDTO) {

        Tickets ticket = ticketsRepo.findById(paymentsDTO.getTicket())
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        PaymentMethod method = methodRepo.findById(paymentsDTO.getPaymentMethod()).orElseThrow(() -> new NotFoundException("PaymentMethod not found"));

        //VALIDATE IF EXISTS
        Payments payments = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Payments not found"));
        payments.setAmount(paymentsDTO.getAmount());
        payments.setTicket(ticket);
        payments.setPaymentMethod(method);
        return Mapper.toDTO(repo.save(payments));


    }

    @Override
    public void deletePayment(Long id) {
        Payments payments = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Payments not found"));
        repo.delete(payments);

    }
}
