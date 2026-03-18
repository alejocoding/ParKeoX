package com.ParkeoX.ParkeoX.DTO.request.ticketsDTO;


import com.ParkeoX.ParkeoX.models.Payments;
import com.ParkeoX.ParkeoX.models.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TicketsRequestDTO {

    private Long id;
    private String vehicle;
    private LocalDateTime checkInAt;
    private LocalDateTime checkOutAt;
    private Long tariff;
    private List<Payments> payments;
    private Long status;
    private Double total;
}
