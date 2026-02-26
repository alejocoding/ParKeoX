package com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO;

import com.ParkeoX.ParkeoX.models.Brand;
import com.ParkeoX.ParkeoX.models.VehicleType;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class VehiclesDTO {

    private String plateNo;
    private Brand brand;
    private String model;
    private String color;
    private VehicleType vehicleType;
    private LocalDateTime createdAt;
}
