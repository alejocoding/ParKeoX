package com.ParkeoX.ParkeoX.services.brand;

import com.ParkeoX.ParkeoX.DTO.request.brandDTO.BrandDTO;
import com.ParkeoX.ParkeoX.models.Brand;

import java.util.List;

public interface IBrandService {

    List<BrandDTO> findAll();
    BrandDTO createBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(Long id,BrandDTO brandDTO);
    void deleteBrand(Long id);
}
