package com.ParkeoX.ParkeoX.DTO.request.licensesDTO;


import com.ParkeoX.ParkeoX.models.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LicensesResponseDTO {

    private String idLicense;
    private String company;
    private String licenseType;
    private Double price;
    private LocalDateTime beginAt;
    private LocalDateTime endAt;
    private String status;
    private LocalDateTime createdAt;
}
