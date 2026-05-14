package com.ParkeoX.ParkeoX.security.auth;

import com.ParkeoX.ParkeoX.models.Users;
import com.ParkeoX.ParkeoX.repository.usersRepository.UsersRepository;
import com.ParkeoX.ParkeoX.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(AuthRequest request) {

        Optional<Users> optionalUser = usersRepository.findByEmail(request.getEmail());

        if(optionalUser.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas, intentelo de nuevo");
        }

        Users user = optionalUser.get();

        // 🔐 VALIDACIÓN REAL
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }

        if(user.getStatus().getId() == 2){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User inactive");
        }

        String token = jwtService.generateToken(user.getName(),user.getEmail(), user.getRole().getRol(), user.getStatus().getStatus(),user.getCompany().getNit());

        return ResponseEntity.ok( new AuthResponse(token));
    }
}
