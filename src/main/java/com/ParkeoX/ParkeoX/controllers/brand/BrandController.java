package com.ParkeoX.ParkeoX.controllers.brand;

import com.ParkeoX.ParkeoX.DTO.request.brandDTO.BrandDTO;
import com.ParkeoX.ParkeoX.models.Brand;
import com.ParkeoX.ParkeoX.services.brand.IBrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/basics/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;


    @GetMapping
    public ResponseEntity<List<BrandDTO>> findAll(){
        return ResponseEntity.ok(brandService.findAll());
    }

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody BrandDTO dto){
        BrandDTO created = brandService.createBrand(dto);
        return ResponseEntity.created(URI.create("/basics/brand" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable long id, @RequestBody BrandDTO dto){
        return ResponseEntity.ok(brandService.updateBrand(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable long id){
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
