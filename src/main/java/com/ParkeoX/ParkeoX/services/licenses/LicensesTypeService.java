package com.ParkeoX.ParkeoX.services.licenses;


import com.ParkeoX.ParkeoX.DTO.request.licenseTypeDTO.LicenseTypeDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.LicenseType;
import com.ParkeoX.ParkeoX.repository.licenseTypeRepository.LicenseTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LicensesTypeService implements ILicencesType {


    private final LicenseTypeRepository repo;

    @Override
    public List<LicenseTypeDTO> getLicensesType() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public LicenseTypeDTO createLicenseType(LicenseTypeDTO licenseTypeDTO) {

       LicenseType licenseType = LicenseType.builder()
               .name(licenseTypeDTO.getName())
               .MonthDuration(licenseTypeDTO.getMonthDuration())
               .createdAt(licenseTypeDTO.getCreatedAt())
               .build();

       return Mapper.toDTO(repo.save(licenseType));
    }

    @Override
    public LicenseTypeDTO updateLicenseType(Long id, LicenseTypeDTO licenseTypeDTO) {

        LicenseType licenseType = repo.findById(id).orElseThrow(()->new NotFoundException("License type not found"));

        licenseType.setName(licenseTypeDTO.getName());
        licenseType.setMonthDuration(licenseTypeDTO.getMonthDuration());

        return Mapper.toDTO(repo.save(licenseType));
    }

    @Override
    public Void deleteLicenseType(Long id) {
        LicenseType licenseType = repo.findById(id).orElseThrow(()->new NotFoundException("License type not found to delete"));
        repo.delete(licenseType);
        return null;
    }
}
