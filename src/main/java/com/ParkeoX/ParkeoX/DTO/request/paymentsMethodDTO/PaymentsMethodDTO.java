package com.ParkeoX.ParkeoX.DTO.request.paymentsMethodDTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentsMethodDTO {
    private Long id;
    private String name;
    private Boolean active;
}
