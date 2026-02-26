package com.ParkeoX.ParkeoX.DTO.request.usersDTO;

import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.Status;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDTO {

    private String cedula;
    private String name;
    private String email;
    private Integer tel;
    private Status role;
    private Company company;
    private Status status;
    private LocalDateTime createdAt;
}
