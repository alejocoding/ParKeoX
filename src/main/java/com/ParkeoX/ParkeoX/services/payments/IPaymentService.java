package com.ParkeoX.ParkeoX.services.payments;

import com.ParkeoX.ParkeoX.DTO.request.paymentsDTO.PaymentsDTO;


import java.util.List;

public interface IPaymentService {

    List<PaymentsDTO> findAll();
    PaymentsDTO createPayment(PaymentsDTO paymentsDTO);
    PaymentsDTO updatePayment(Long id, PaymentsDTO paymentsDTO);
    void deletePayment(Long id);
}

