package com.ParkeoX.ParkeoX.services.vehicle;


import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesResponseDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Brand;
import com.ParkeoX.ParkeoX.models.VehicleType;
import com.ParkeoX.ParkeoX.models.Vehicles;
import com.ParkeoX.ParkeoX.repository.brandRepository.BrandRepository;
import com.ParkeoX.ParkeoX.repository.vehiclesRepository.VehicleTypeRepository;
import com.ParkeoX.ParkeoX.repository.vehiclesRepository.VehiclesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class VehicleService implements IVehicleService {

    private final VehiclesRepository repo;
    private final BrandRepository brandRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public List<VehiclesResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public VehiclesRequestDTO saveVehicle(VehiclesRequestDTO vehicleDTO) {

        //GET THE ID FROM THE BRAND
        Brand brand = brandRepository.findById(vehicleDTO.getBrand()).orElseThrow(() -> new RuntimeException("Brand not found"));

        VehicleType type = vehicleTypeRepository.findById(vehicleDTO.getVehicleType()).orElseThrow(() -> new RuntimeException("VehicleType not found"));

        Vehicles vehicle = Vehicles.builder()
                .plateNo(vehicleDTO.getPlateNo())
                .model(vehicleDTO.getModel())
                .color(vehicleDTO.getColor())
                .vehicleType(type)
                .brand(brand)
                .build();

        return Mapper.toRequestDTO(repo.save(vehicle));
    }

    @Override
    public VehiclesRequestDTO updateVehicle(String plateNo, VehiclesRequestDTO vehicleDTO) {
        //GET THE ID FROM THE BRAND
        Brand brand = brandRepository.findById(vehicleDTO.getBrand()).orElseThrow(() -> new RuntimeException("Brand not found"));

        VehicleType type = vehicleTypeRepository.findById(vehicleDTO.getVehicleType()).orElseThrow(() -> new RuntimeException("VehicleType not found"));

        Vehicles vehicle = repo.findByPlateNo(plateNo).orElseThrow(() -> new NotFoundException("Vehicle not exist"));

            vehicle.setBrand(brand);
            vehicle.setModel(vehicleDTO.getModel());
            vehicle.setColor( vehicleDTO.getColor());
            vehicle.setVehicleType(type);

            return  Mapper.toRequestDTO(repo.save(vehicle));

    }

    @Transactional
    public Void deleteVehicle(String plateNo) {
        Vehicles delete = repo.findByPlateNo(plateNo).orElseThrow(()->new NotFoundException("Vehiculo not found"));
        repo.delete(delete);
        return null;
    }
}
