package com.ParkeoX.ParkeoX.controllers.users;

import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserResponseDTO;
import com.ParkeoX.ParkeoX.services.users.IUserService;
import com.ParkeoX.ParkeoX.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/basics/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final  IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok().body(userService.findAll(page));
    }

    @GetMapping("/company/{nit}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByCompany(@PathVariable String nit) {
        return ResponseEntity.ok().body(userService.findByCompany(nit));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO User = userService.createUser(userRequestDTO);
        return ResponseEntity.created(URI.create("/users")).body(User);
    }

    @PutMapping("/{cedula}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String cedula, @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(cedula, userRequestDTO));
    }

    @DeleteMapping("/{cedula}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String cedula) {
        userService.deleteUser(cedula);
        return ResponseEntity.noContent().build();
    }

}
