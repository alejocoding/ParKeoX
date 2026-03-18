package com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VehiclesResponseDTO {

    private String plateNo;
    private String brand;
    private String model;
    private String color;
    private String vehicleType;
    private LocalDateTime createdAt;
}
