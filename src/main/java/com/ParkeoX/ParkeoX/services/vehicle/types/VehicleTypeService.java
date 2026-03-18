package com.ParkeoX.ParkeoX.services.vehicle.types;


import com.ParkeoX.ParkeoX.DTO.request.vehicleTypeDTO.VehicleTypeDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.VehicleType;
import com.ParkeoX.ParkeoX.repository.vehiclesRepository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class VehicleTypeService implements IVehicleTypeService {

    private final VehicleTypeRepository repo;


    @Override
    public List<VehicleTypeDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public VehicleTypeDTO saveVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = VehicleType.builder()
                .name(vehicleTypeDTO.getName())
                .build();

        return Mapper.toDTO(repo.save(vehicleType));
    }

    @Override
    public VehicleTypeDTO updateVehicleType(Long id, VehicleTypeDTO vehicleTypeDTO) {

        //IF EXIST
        VehicleType vehicleType = repo.findById(id).orElseThrow(() -> new NotFoundException("VehicleType not found"));
        vehicleType.setName(vehicleTypeDTO.getName());
        return  Mapper.toDTO(repo.save(vehicleType));
    }

    @Override
    public Void deleteVehicleType(Long id) {
        //IF EXIST
        VehicleType vehicleType = repo.findById(id).orElseThrow(() -> new NotFoundException("VehicleType not found to delete"));
        repo.deleteById(id);
        return null;
    }
}
