package com.ParkeoX.ParkeoX.security.auth;

import com.ParkeoX.ParkeoX.constants.StatusConstants;
import com.ParkeoX.ParkeoX.models.Licenses;
import com.ParkeoX.ParkeoX.models.Users;
import com.ParkeoX.ParkeoX.repository.licensesRepository.LicenseRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import com.ParkeoX.ParkeoX.repository.usersRepository.UsersRepository;
import com.ParkeoX.ParkeoX.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final LicenseRepository licenseRepository;
    private final StatusRepository statusRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(AuthRequest request) {

        Optional<Users> optionalUser = usersRepository.findByEmail(request.getEmail());

        if(optionalUser.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Credenciales incorrectas, intentelo de nuevo"));
        }

        Users user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Credenciales incorrectas, intentelo de nuevo"));
        }

        if (StatusConstants.INACTIVE_ID.equals(user.getStatus().getId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Usuario inactivo"));
        }

        if (user.getCompany() != null) {
            ResponseEntity<?> licenseError = validateCompanyLicense(user.getCompany().getId());
            if (licenseError != null) {
                return licenseError;
            }
        }

        String token = jwtService.generateToken(
                user.getName(),
                user.getEmail(),
                user.getRole().getRol(),
                user.getStatus().getStatus(),
                user.getCompany() != null ? user.getCompany().getNit() : null
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }

    private ResponseEntity<?> validateCompanyLicense(Long companyId) {
        Optional<Licenses> latestLicense = licenseRepository.findFirstByCompany_IdOrderByEndAtDesc(companyId);

        if (latestLicense.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Tu compañía no tiene una licencia registrada. Por favor contáctate al 314 282 2521."));
        }

        Licenses license = latestLicense.get();

        if (license.getEndAt() != null && license.getEndAt().isBefore(LocalDateTime.now())) {
            if (!StatusConstants.INACTIVE_ID.equals(license.getStatus().getId())) {
                statusRepository.findById(StatusConstants.INACTIVE_ID).ifPresent(inactive -> {
                    license.setStatus(inactive);
                    licenseRepository.save(license);
                });
            }
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Licencia vencida. Por favor contáctate al 314 282 2521 para renovarla."));
        }

        return null;
    }
}
