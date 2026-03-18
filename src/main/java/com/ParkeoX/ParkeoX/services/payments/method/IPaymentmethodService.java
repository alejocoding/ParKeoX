package com.ParkeoX.ParkeoX.services.payments.method;

import com.ParkeoX.ParkeoX.DTO.request.paymentsMethodDTO.PaymentsMethodDTO;


import java.util.List;

public interface IPaymentmethodService {

    List<PaymentsMethodDTO> findAll();
    PaymentsMethodDTO createPayment(PaymentsMethodDTO paymentDTO);
    PaymentsMethodDTO updatePayment(Long id,PaymentsMethodDTO paymentDTO);
    void deletePayment(Long id);
}
