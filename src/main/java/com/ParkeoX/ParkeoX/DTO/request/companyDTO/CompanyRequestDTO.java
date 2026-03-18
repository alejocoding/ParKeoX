package com.ParkeoX.ParkeoX.DTO.request.companyDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyRequestDTO {

    private Long id;
    private String nit;
    private String name;
    private String address;
    private Long status;
    private LocalDateTime createdAt;



}
