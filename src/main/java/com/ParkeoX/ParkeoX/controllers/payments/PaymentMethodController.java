package com.ParkeoX.ParkeoX.controllers.payments;


import com.ParkeoX.ParkeoX.DTO.request.paymentsMethodDTO.PaymentsMethodDTO;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.PaymentMethod;
import com.ParkeoX.ParkeoX.services.payments.PaymentService;
import com.ParkeoX.ParkeoX.services.payments.method.IPaymentmethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("basics/paymentsMethod")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final IPaymentmethodService paymentService;


    @GetMapping
    public ResponseEntity<List<PaymentsMethodDTO>> getAllPaymentMethods() {

        return ResponseEntity.ok(paymentService.findAll());

    }

    @PostMapping
    public ResponseEntity<PaymentsMethodDTO> createPaymentMethod(@RequestBody PaymentsMethodDTO paymentsMethodDTO) {
        PaymentsMethodDTO created = paymentService.createPayment(paymentsMethodDTO);

        return ResponseEntity.created(URI.create("/basics/payments" + created.getId())).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PaymentsMethodDTO> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentsMethodDTO paymentMethodDTO) {

        return ResponseEntity.ok(paymentService.updatePayment(id,paymentMethodDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long id) {

        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }



}
