package com.ParkeoX.ParkeoX.DTO.request.vehicleTypeDTO;


import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VehicleTypeDTO {
    private Long id;
    private String name;

}
