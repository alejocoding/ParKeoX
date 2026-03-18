package com.ParkeoX.ParkeoX.DTO.request.companyDTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyResponseDTO {

    private Long id;
    private String nit;
    private String name;
    private String address;
    private String status;
    private LocalDateTime createdAt;

}
