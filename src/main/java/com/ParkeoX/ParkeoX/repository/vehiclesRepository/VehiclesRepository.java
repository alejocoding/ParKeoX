package com.ParkeoX.ParkeoX.repository.vehiclesRepository;

import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiclesRepository extends JpaRepository<Vehicles,String> {

        Optional<Vehicles> findByPlateNo(String plateNo);

}

