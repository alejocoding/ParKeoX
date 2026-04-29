package com.ParkeoX.ParkeoX.controllers.licenses;


import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesResponseDTO;
import com.ParkeoX.ParkeoX.services.licenses.ILicensesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("advanced/licenses")
@AllArgsConstructor
public class LicenseController {


    private  final ILicensesService service;
    @GetMapping
    public ResponseEntity<List<LicensesResponseDTO>> findAll() {

        return ResponseEntity.ok().body(service.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<LicensesResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LicensesRequestDTO> create(@RequestBody LicensesRequestDTO request) {
        return ResponseEntity.ok(service.createLicense(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicensesResponseDTO> update(@PathVariable String id,@RequestBody LicensesRequestDTO request) {
        return ResponseEntity.ok(service.updateLicense(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteLicense(id);
        return ResponseEntity.noContent().build();
    }
}
