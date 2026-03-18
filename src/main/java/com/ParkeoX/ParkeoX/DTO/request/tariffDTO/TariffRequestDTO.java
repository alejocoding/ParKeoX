package com.ParkeoX.ParkeoX.DTO.request.tariffDTO;

import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TariffRequestDTO {

    private Long id;
    private Long company;
    private Long vehicleType;
    private Double price;
    private Boolean active;
    private LocalDateTime createdAt;
}
