package com.ParkeoX.ParkeoX.repository.brandRepository;

import com.ParkeoX.ParkeoX.models.Brand;
import com.ParkeoX.ParkeoX.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BrandRepository extends JpaRepository<Brand,Long> {

    Optional<Company> findByBrand(String brand);
}
