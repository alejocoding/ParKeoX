package com.ParkeoX.ParkeoX.repository.paymentsMethod;


import com.ParkeoX.ParkeoX.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsMethodRepository extends JpaRepository<PaymentMethod,Long> {
}
