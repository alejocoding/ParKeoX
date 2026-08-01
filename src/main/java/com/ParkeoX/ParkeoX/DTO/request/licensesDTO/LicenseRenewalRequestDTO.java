package com.ParkeoX.ParkeoX.DTO.request.licensesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class LicenseRenewalRequestDTO {
    private Long id;
    private String idLicense;
    private String company;
    private String requestedByEmail;
    private boolean resolved;
    private LocalDateTime requestedAt;
}
