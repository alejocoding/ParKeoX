package com.ParkeoX.ParkeoX.controllers.vehicle;


import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesResponseDTO;
import com.ParkeoX.ParkeoX.services.vehicle.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("advanced/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private  final IVehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehiclesResponseDTO>> getAllVehicles() {
        return ResponseEntity.ok().body(vehicleService.findAll());
    }

    @PostMapping
    public ResponseEntity<VehiclesRequestDTO> createVehicle(@RequestBody VehiclesRequestDTO vehiclesRequestDTO) {
        VehiclesRequestDTO created = vehicleService.saveVehicle(vehiclesRequestDTO);
        return ResponseEntity.created(URI.create("advanced/vehicle" + created.getPlateNo())).body(created);

    }


    @PutMapping("/{plateNo}")
    public ResponseEntity<VehiclesRequestDTO> updateVehicle(@PathVariable String plateNo, @RequestBody VehiclesRequestDTO vehiclesRequestDTO) {

        return ResponseEntity.ok().body(vehicleService.updateVehicle(plateNo, vehiclesRequestDTO));
    }


    @DeleteMapping("/{plateNo}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String plateNo) {
        vehicleService.deleteVehicle(plateNo);
        return ResponseEntity.noContent().build();
    }
}
