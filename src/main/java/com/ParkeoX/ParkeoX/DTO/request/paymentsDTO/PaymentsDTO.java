package com.ParkeoX.ParkeoX.DTO.request.paymentsDTO;


import com.ParkeoX.ParkeoX.models.PaymentMethod;
import com.ParkeoX.ParkeoX.models.Tickets;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentsDTO {
    private Long id;
    private Long ticket;
    private Long PaymentMethod;
    private Double amount;
    private LocalDateTime paymentDate;
}
