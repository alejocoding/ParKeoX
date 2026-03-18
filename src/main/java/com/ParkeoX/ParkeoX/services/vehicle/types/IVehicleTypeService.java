package com.ParkeoX.ParkeoX.services.vehicle.types;

import com.ParkeoX.ParkeoX.DTO.request.vehicleTypeDTO.VehicleTypeDTO;

import java.util.List;

public interface IVehicleTypeService {

     List<VehicleTypeDTO> findAll();
     VehicleTypeDTO saveVehicleType(VehicleTypeDTO vehicleTypeDTO);
     VehicleTypeDTO updateVehicleType(Long id,VehicleTypeDTO vehicleTypeDTO);
     Void deleteVehicleType(Long id);
}
