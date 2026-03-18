package com.ParkeoX.ParkeoX.DTO.request.licensesDTO;

import com.ParkeoX.ParkeoX.models.LicenseType;
import com.ParkeoX.ParkeoX.models.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LicensesDTO {

    private String id_license;
    private Long licenseType;
    private Double price;
    private LocalDateTime beginAt;
    private LocalDateTime endAt;
    private Status status;
    private LocalDateTime createdAt;
}
