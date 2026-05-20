package com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO;

import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class VehiclesRequestDTO {

    private String plateNo;
    private Long vehicleType;
    private LocalDateTime createdAt;
}
