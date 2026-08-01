package com.ParkeoX.ParkeoX.controllers.licenses;


import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicenseRenewalRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesResponseDTO;
import com.ParkeoX.ParkeoX.services.licenses.ILicensesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/company/{nit}")
    public ResponseEntity<List<LicensesResponseDTO>> findByCompany(@PathVariable String nit) {
        return ResponseEntity.ok().body(service.findByCompany(nit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicensesResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<LicensesResponseDTO> create(@RequestBody LicensesRequestDTO request) {
        return ResponseEntity.ok(service.createLicense(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<LicensesResponseDTO> update(@PathVariable String id,@RequestBody LicensesRequestDTO request) {
        return ResponseEntity.ok(service.updateLicense(id, request));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteLicense(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/renewal-request")
    public ResponseEntity<LicenseRenewalRequestDTO> requestRenewal(@PathVariable String id, Authentication authentication) {
        return ResponseEntity.ok(service.requestRenewal(id, authentication.getName()));
    }

    @GetMapping("/renewal-requests")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<List<LicenseRenewalRequestDTO>> getRenewalRequests() {
        return ResponseEntity.ok(service.getRenewalRequests());
    }

    @PutMapping("/renewal-requests/{id}/resolve")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<LicenseRenewalRequestDTO> resolveRenewalRequest(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolveRenewalRequest(id));
    }
}
