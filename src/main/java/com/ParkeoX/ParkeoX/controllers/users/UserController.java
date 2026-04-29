package com.ParkeoX.ParkeoX.controllers.users;

import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserResponseDTO;
import com.ParkeoX.ParkeoX.services.users.IUserService;
import com.ParkeoX.ParkeoX.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO User = userService.createUser(userRequestDTO);
        return ResponseEntity.created(URI.create("/users")).body(User);
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String cedula, @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> deleteUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.noContent().build();

    }

}
