package com.ParkeoX.ParkeoX.repository.passwordResetRepository;

import com.ParkeoX.ParkeoX.models.PasswordResetCode;
import com.ParkeoX.ParkeoX.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long> {

    Optional<PasswordResetCode> findByUserAndCodeAndUsedFalseAndVerifiedFalse(Users user, String code);

    Optional<PasswordResetCode> findByResetTokenAndVerifiedTrueAndUsedFalse(String resetToken);

    List<PasswordResetCode> findAllByUserAndUsedFalse(Users user);
}
