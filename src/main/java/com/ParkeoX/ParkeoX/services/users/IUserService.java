package com.ParkeoX.ParkeoX.services.users;

import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserResponseDTO;

import java.util.List;

public interface IUserService {

    List<UserResponseDTO> findAll();
    UserResponseDTO createUser(UserRequestDTO userDTO);
    UserRequestDTO updateUser(String CC, UserRequestDTO userDTO);
    Void deleteUser(String CC);
}
