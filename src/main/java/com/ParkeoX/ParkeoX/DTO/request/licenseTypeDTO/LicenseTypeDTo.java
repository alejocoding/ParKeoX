package com.ParkeoX.ParkeoX.DTO.request.licenseTypeDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LicenseTypeDTo {

    private Long id;
    private String name;
    private Long MonthDuration;
    private LocalDateTime createdAt;
}
