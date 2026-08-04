package com.ParkeoX.ParkeoX.DTO.request.tariffDTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TariffResponseDTO {

    private Long id;
    private String name;
    private String company;
    private String vehicleType;
    private Double price;
    private Double minutePrice;
    private Boolean active;
    private LocalDateTime createdAt;
}
