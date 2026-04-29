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

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(AuthRequest request) {

        Users user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

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

        String token = jwtService.generateToken(user.getEmail(), user.getRole().getRol(), user.getStatus().getStatus());

        return ResponseEntity.ok( new AuthResponse(token));
    }
}
