package com.ParkeoX.ParkeoX.services.users;


import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserResponseDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.Roles;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.models.Users;
import com.ParkeoX.ParkeoX.repository.companyRepository.CompanyRepository;
import com.ParkeoX.ParkeoX.repository.rolesRepository.RolesRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import com.ParkeoX.ParkeoX.repository.usersRepository.UsersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements IUserService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> findAll() {
        return usersRepository.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDTO) {

        Status status = statusRepository.findById(userDTO.getStatus()).orElseThrow(() -> new NotFoundException("Ticket not found"));
        Company company = companyRepository.findById(userDTO.getCompany()).orElseThrow(() -> new NotFoundException("Company not found"));
        Roles rol =  rolesRepository.findById(userDTO.getRole()).orElseThrow(() -> new NotFoundException("Role not found"));

        //Validate Email AND Identification

        Optional<Users> email = usersRepository.findByEmail(userDTO.getEmail());
        Optional<Users> cedula = usersRepository.findByCedula(userDTO.getCedula());
        if (email.isPresent()) throw new RuntimeException("Email already exists");
        if (cedula.isPresent()) throw new RuntimeException("cedula already exists");


        Users usuario = Users.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .cedula(userDTO.getCedula())
                .tel(userDTO.getTel())
                .role(rol)
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .status(status)
                .company(company)
                .build();

        return Mapper.toResponseDTO(usersRepository.save(usuario));

    }

    @Override
    public UserRequestDTO updateUser(String CC, UserRequestDTO userDTO) {
        return null;
    }

    @Override
    public Void deleteUser(String CC) {
        return null;
    }
}
