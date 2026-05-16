package com.ParkeoX.ParkeoX.repository.tariffRepository;

import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TariffRepository extends JpaRepository<Tariff,Long> {

    List<Tariff> findByCompanyNit(String nit);
}
