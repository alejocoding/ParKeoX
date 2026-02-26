package com.ParkeoX.ParkeoX.DTO.request.ticketsDTO;


import com.ParkeoX.ParkeoX.models.Payments;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.models.Tariff;
import com.ParkeoX.ParkeoX.models.Vehicles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TicketsDTO {

    private Long id;
    private Vehicles vehicle;
    private LocalDateTime checkInAt;
    private LocalDateTime checkOutAt;
    private Tariff tariff;
    private List<Payments> payments;
    private Status status;
    private Double total;
}
