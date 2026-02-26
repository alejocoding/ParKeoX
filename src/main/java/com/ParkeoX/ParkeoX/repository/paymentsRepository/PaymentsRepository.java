package com.ParkeoX.ParkeoX.repository.paymentsRepository;

import com.ParkeoX.ParkeoX.models.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments,Long> {
}
