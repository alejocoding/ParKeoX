package com.ParkeoX.ParkeoX.DTO.request.usersDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponseDTO {
    private Long id;
    private String cedula;
    private String name;
    private String email;
    private Long tel;
    private String role;
    private String company;
    private String status;
    private LocalDateTime createdAt;
}
