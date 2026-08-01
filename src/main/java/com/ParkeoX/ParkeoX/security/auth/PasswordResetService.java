package com.ParkeoX.ParkeoX.security.auth;

import com.ParkeoX.ParkeoX.models.PasswordResetCode;
import com.ParkeoX.ParkeoX.models.Users;
import com.ParkeoX.ParkeoX.repository.passwordResetRepository.PasswordResetCodeRepository;
import com.ParkeoX.ParkeoX.repository.usersRepository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UsersRepository usersRepository;
    private final PasswordResetCodeRepository passwordResetCodeRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${app.password-reset.from}")
    private String fromAddress;

    @Value("${app.password-reset.code-expiration-minutes}")
    private long codeExpirationMinutes;

    public void forgotPassword(String email) {
        Optional<Users> userOpt = usersRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return;
        }

        Users user = userOpt.get();

        List<PasswordResetCode> pendingCodes = passwordResetCodeRepository.findAllByUserAndUsedFalse(user);
        pendingCodes.forEach(c -> c.setUsed(true));
        passwordResetCodeRepository.saveAll(pendingCodes);

        String code = generateCode();

        PasswordResetCode resetCode = PasswordResetCode.builder()
                .user(user)
                .code(code)
                .verified(false)
                .used(false)
                .expiresAt(LocalDateTime.now().plusMinutes(codeExpirationMinutes))
                .build();

        passwordResetCodeRepository.save(resetCode);

        sendCodeEmail(user.getEmail(), code);
    }

    public VerifyCodeResponse verifyCode(String email, String code) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid or expired code"));

        PasswordResetCode resetCode = passwordResetCodeRepository
                .findByUserAndCodeAndUsedFalseAndVerifiedFalse(user, code)
                .orElseThrow(() -> new RuntimeException("Invalid or expired code"));

        if (resetCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired code");
        }

        resetCode.setVerified(true);
        resetCode.setResetToken(UUID.randomUUID().toString());
        passwordResetCodeRepository.save(resetCode);

        return new VerifyCodeResponse(resetCode.getResetToken());
    }

    public void resetPassword(String resetToken, String newPassword) {
        if (newPassword == null || newPassword.length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters long");
        }

        PasswordResetCode resetCode = passwordResetCodeRepository
                .findByResetTokenAndVerifiedTrueAndUsedFalse(resetToken)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (resetCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired token");
        }

        Users user = resetCode.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(user);

        resetCode.setUsed(true);
        passwordResetCodeRepository.save(resetCode);
    }

    private String generateCode() {
        return String.format("%06d", secureRandom.nextInt(1_000_000));
    }

    private void sendCodeEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(to);
        message.setSubject("Código de recuperación de contraseña - ParkeoX");
        message.setText("Tu código de verificación es: " + code
                + "\n\nEste código vence en " + codeExpirationMinutes + " minutos."
                + "\n\nSi no solicitaste este cambio, puedes ignorar este correo.");

        mailSender.send(message);
    }
}
