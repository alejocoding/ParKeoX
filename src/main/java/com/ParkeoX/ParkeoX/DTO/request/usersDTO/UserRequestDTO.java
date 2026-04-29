package com.ParkeoX.ParkeoX.DTO.request.usersDTO;

import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    private Long id;
    private String cedula;
    private String name;
    private String email;
    private Long tel;
    private String password;
    private Long role;
    private Long company;
    private Long status;
    private LocalDateTime createdAt;
}
