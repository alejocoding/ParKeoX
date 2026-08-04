package com.ParkeoX.ParkeoX.DTO.request.licensesDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LicensesRequestDTO {

    private String idLicense;
    private Long company;
    private Long licenseType;
    private Double price;
    private LocalDateTime beginAt;
    private LocalDateTime endAt;
    private Integer maxUsers;
    private Long status;
    private LocalDateTime createdAt;
}
