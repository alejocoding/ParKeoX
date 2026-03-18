package com.ParkeoX.ParkeoX.controllers.vehicle.type;


import com.ParkeoX.ParkeoX.DTO.request.vehicleTypeDTO.VehicleTypeDTO;
import com.ParkeoX.ParkeoX.models.VehicleType;
import com.ParkeoX.ParkeoX.services.vehicle.types.IVehicleTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("basics/vehicleType")
@RequiredArgsConstructor

public class VehicleTypeController {


    private final IVehicleTypeService vehicleTypeService;

    @GetMapping
    public ResponseEntity<List<VehicleTypeDTO>> getAllVehicleTypes() {
        return ResponseEntity.ok().body(vehicleTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<VehicleTypeDTO> createVehicleType(@RequestBody VehicleTypeDTO vehicleTypeDTO) {

        VehicleTypeDTO created = vehicleTypeService.saveVehicleType(vehicleTypeDTO);

        return ResponseEntity.created(URI.create("/vehicleType/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleTypeDTO> updateVehicleType(@PathVariable Long id, @RequestBody VehicleTypeDTO vehicleTypeDTO) {


        return ResponseEntity.ok().body(vehicleTypeService.updateVehicleType(id, vehicleTypeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable Long id) {
        vehicleTypeService.deleteVehicleType(id);
        return ResponseEntity.noContent().build();

    }
}
