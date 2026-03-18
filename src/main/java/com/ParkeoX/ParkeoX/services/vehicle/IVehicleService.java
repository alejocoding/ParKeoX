package com.ParkeoX.ParkeoX.services.vehicle;


import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesResponseDTO;

import java.util.List;

public interface IVehicleService {

    List<VehiclesResponseDTO> findAll();
    VehiclesRequestDTO saveVehicle(VehiclesRequestDTO vehicleDTO);
    VehiclesRequestDTO updateVehicle(String plateId, VehiclesRequestDTO vehicleDTO);
    Void deleteVehicle(String plateId);
}
