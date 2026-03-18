package com.ParkeoX.ParkeoX.controllers.licenses;

import com.ParkeoX.ParkeoX.DTO.request.licenseTypeDTO.LicenseTypeDTO;
import com.ParkeoX.ParkeoX.services.licenses.ILicencesTypeService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("basics/licensesType")
public class LicensesTypeController {

    private final ILicencesTypeService repo;

    @GetMapping
    public ResponseEntity<List<LicenseTypeDTO>> getAllLicenses() {
        return ResponseEntity.ok().body(repo.getLicensesType());
    }

    @PostMapping
    public ResponseEntity<LicenseTypeDTO> createLicenses(@RequestBody LicenseTypeDTO licenseTypeDTO) {
        return ResponseEntity.created(URI.create("/licensesType")).body(repo.createLicenseType(licenseTypeDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<LicenseTypeDTO> updateLicenses(@PathVariable Long id, @RequestBody LicenseTypeDTO licenseTypeDTO) {
        return ResponseEntity.ok().body(repo.updateLicenseType(id, licenseTypeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicenses(@PathVariable Long id) {
        repo.deleteLicenseType(id);
        return ResponseEntity.noContent().build();
    }
}
