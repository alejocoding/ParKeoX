package com.ParkeoX.ParkeoX.DTO.request.tariffDTO;

import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.VehicleType;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class tariffDTO {

    private Long id;
    private Company company;
    private VehicleType vehicleType;
    private Double price;
    private Boolean active;
    private LocalDateTime createdAt;
}
