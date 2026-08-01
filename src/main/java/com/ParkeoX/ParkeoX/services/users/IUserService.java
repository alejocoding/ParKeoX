package com.ParkeoX.ParkeoX.services.users;

import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {

    Page<UserResponseDTO> findAll(int page);
    List<UserResponseDTO> findByCompany(String nit);
    UserResponseDTO createUser(UserRequestDTO userDTO);
    UserResponseDTO updateUser(String cedula, UserRequestDTO userDTO);
    void deleteUser(String cedula);
}
