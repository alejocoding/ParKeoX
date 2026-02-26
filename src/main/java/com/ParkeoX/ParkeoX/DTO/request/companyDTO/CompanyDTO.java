package com.ParkeoX.ParkeoX.DTO.request.companyDTO;

import com.ParkeoX.ParkeoX.models.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyDTO {

    private Long id;
    private String name;
    private String address;
    private Status status;
    private LocalDateTime createdAt;



}
