package com.ParkeoX.ParkeoX.controllers.payments;


import com.ParkeoX.ParkeoX.DTO.request.paymentsDTO.PaymentsDTO;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.services.payments.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("advanced/payment")
@RequiredArgsConstructor

public class PaymentsController {

    private final IPaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentsDTO>> getPayments() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @PostMapping
    public ResponseEntity<PaymentsDTO> createPayment(@RequestBody PaymentsDTO paymentsDTO) {
        PaymentsDTO created = paymentService.createPayment(paymentsDTO);
        return ResponseEntity.created(URI.create("advanced/payment" + created.getId())).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PaymentsDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentsDTO paymentsDTO) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }



}
