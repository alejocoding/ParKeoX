package com.ParkeoX.ParkeoX.controllers.company;


import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyResponseDTO;
import com.ParkeoX.ParkeoX.services.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/basics/company")
public class companyController {

    @Autowired
    ICompanyService companyService;


    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> getCompanies() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<CompanyRequestDTO> createCompany(@RequestBody CompanyRequestDTO dto) {
        CompanyRequestDTO created = companyService.createCompany(dto);
        return ResponseEntity.created(URI.create("/basics/brand" + created.getId())).body(created);
    }

    @PutMapping("/{nit}")
    public ResponseEntity<CompanyRequestDTO> updateCompany(@PathVariable String nit, @RequestBody CompanyRequestDTO dto){
        return ResponseEntity.ok(companyService.updateCompany(nit, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable long id){
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
