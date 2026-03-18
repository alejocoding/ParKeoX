package com.ParkeoX.ParkeoX.services.brand;

import com.ParkeoX.ParkeoX.DTO.request.brandDTO.BrandDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Brand;
import com.ParkeoX.ParkeoX.repository.brandRepository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService{

    @Autowired
    private BrandRepository repo;

    @Override
    public List<BrandDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {

        Brand brand = Brand.builder()
                .id(brandDTO.getId())
                .Brand(brandDTO.getBrand())
                .build();

        return Mapper.toDTO(repo.save(brand));
    }

    @Override
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
            //Search if exist
        Brand brand = repo.findById(id).orElseThrow(()->new NotFoundException("Brand not found"));

        brand.setBrand(brandDTO.getBrand());
        return Mapper.toDTO(repo.save(brand));
    }

    @Override
    public void deleteBrand(Long id) {

        if(!repo.existsById(id)){
            throw new NotFoundException("Brand not found to delete");
        }
        repo.deleteById(id);
    }
}
