package com.ParkeoX.ParkeoX.services.users;


import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserResponseDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.Licenses;
import com.ParkeoX.ParkeoX.models.Roles;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.models.Users;
import com.ParkeoX.ParkeoX.repository.companyRepository.CompanyRepository;
import com.ParkeoX.ParkeoX.repository.licensesRepository.LicenseRepository;
import com.ParkeoX.ParkeoX.repository.rolesRepository.RolesRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import com.ParkeoX.ParkeoX.repository.usersRepository.UsersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements IUserService {

    private static final int PAGE_SIZE = 30;
    // SUPERADMIN es un rol de plataforma, aun sin modulo propio: no debe ser visible
    // ni administrable por los ADMIN de una compania.
    private static final String SUPERADMIN_ROLE = "SUPERADMIN";

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;
    private final LicenseRepository licenseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponseDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return usersRepository.findAll(pageable).map(Mapper::toResponseDTO);
    }

    @Override
    public List<UserResponseDTO> findByCompany(String nit) {
        return usersRepository.findByCompanyNitExcludingRole(nit, SUPERADMIN_ROLE).stream().map(Mapper::toResponseDTO).toList();
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

        validateUserLimit(company);


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

    private void validateUserLimit(Company company) {
        Optional<Licenses> latestLicense = licenseRepository.findFirstByCompany_IdOrderByEndAtDesc(company.getId());

        if (latestLicense.isEmpty() || latestLicense.get().getMaxUsers() == null) {
            return;
        }

        long currentUsers = usersRepository.countByCompany_Id(company.getId());
        if (currentUsers >= latestLicense.get().getMaxUsers()) {
            throw new RuntimeException("Se alcanzó el número máximo de usuarios permitido por la licencia");
        }
    }

    @Override
    public UserResponseDTO updateUser(String cedula, UserRequestDTO userDTO) {
        Users user = usersRepository.findByCedula(cedula)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(user.getEmail())) {
            usersRepository.findByEmail(userDTO.getEmail()).ifPresent(u -> {
                throw new RuntimeException("Email already exists");
            });
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getCedula() != null && !userDTO.getCedula().equals(user.getCedula())) {
            usersRepository.findByCedula(userDTO.getCedula()).ifPresent(u -> {
                throw new RuntimeException("cedula already exists");
            });
            user.setCedula(userDTO.getCedula());
        }

        if (userDTO.getName() != null) user.setName(userDTO.getName());
        if (userDTO.getTel() != null) user.setTel(userDTO.getTel());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        if (userDTO.getRole() != null) {
            Roles role = rolesRepository.findById(userDTO.getRole())
                    .orElseThrow(() -> new NotFoundException("Role not found"));
            user.setRole(role);
        }

        if (userDTO.getCompany() != null) {
            Company company = companyRepository.findById(userDTO.getCompany())
                    .orElseThrow(() -> new NotFoundException("Company not found"));

            boolean changingCompany = user.getCompany() == null || !user.getCompany().getId().equals(company.getId());
            if (changingCompany) {
                validateUserLimit(company);
            }
            user.setCompany(company);
        }

        if (userDTO.getStatus() != null) {
            Status status = statusRepository.findById(userDTO.getStatus())
                    .orElseThrow(() -> new NotFoundException("Status not found"));
            user.setStatus(status);
        }

        return Mapper.toResponseDTO(usersRepository.save(user));
    }

    @Override
    public void deleteUser(String cedula) {
        Users user = usersRepository.findByCedula(cedula)
                .orElseThrow(() -> new NotFoundException("User not found"));
        usersRepository.delete(user);
    }
}
