package com.ParkeoX.ParkeoX.services.licenses;


import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesResponseDTO;
import com.ParkeoX.ParkeoX.generator.LicenseGenerator;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.LicenseType;
import com.ParkeoX.ParkeoX.models.Licenses;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.repository.companyRepository.CompanyRepository;
import com.ParkeoX.ParkeoX.repository.licenseTypeRepository.LicenseTypeRepository;
import com.ParkeoX.ParkeoX.repository.licensesRepository.LicenseRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.introspect.TypeResolutionContext;

import java.util.List;

@Service
@AllArgsConstructor
public class LicensesService implements ILicensesService{

    private final LicenseRepository repo;
    private final CompanyRepository companyRepository;
    private final LicenseTypeRepository licenseTypeRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<LicensesResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public LicensesResponseDTO findById(String id_license) {
        return repo.findByIdLicense(id_license).map(Mapper::toResponseDTO).orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
    }

    @Override
    public LicensesRequestDTO createLicense(LicensesRequestDTO licensesRequestDTO) {

        Status status = statusRepository.findById(licensesRequestDTO.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("status not found"));
        Company company = companyRepository.findById(licensesRequestDTO.getCompany()).orElseThrow(()-> new RuntimeException("Company not found"));
        LicenseType licenseType = licenseTypeRepository.findById(licensesRequestDTO.getLicenseType()).orElseThrow(()-> new RuntimeException("License Type not found"));

        String uniqueLicence;
        do {
            uniqueLicence = LicenseGenerator.generateLicenseCode(12);
        } while (repo.existsByIdLicense(uniqueLicence));

        Licenses license = Licenses.builder()
                .idLicense(uniqueLicence)
                .company(company)
                .licenseType(licenseType)
                .price(licensesRequestDTO.getPrice())
                .beginAt(licensesRequestDTO.getBeginAt())
                .endAt(licensesRequestDTO.getEndAt())
                .status(status)
                .build();

        return Mapper.toRequestDTO(repo.save(license));
    }

    @Override
    public LicensesResponseDTO updateLicense(String id, LicensesRequestDTO licensesRequestDTO) {

        Licenses license = repo.findByIdLicense(id)
                .orElseThrow(() -> new RuntimeException("Licencia no encontrada"));

        Status status = statusRepository.findById(licensesRequestDTO.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("status not found"));

        Company company = companyRepository.findById(licensesRequestDTO.getCompany())
                .orElseThrow(()-> new RuntimeException("Company not found"));


        LicenseType licenseType = licenseTypeRepository.findById(licensesRequestDTO.getLicenseType())
                .orElseThrow(()-> new RuntimeException("License Type not found"));



        license.setCompany(company);
        license.setLicenseType(licenseType);
        license.setPrice(licensesRequestDTO.getPrice());
        license.setBeginAt(licensesRequestDTO.getBeginAt());
        license.setEndAt(licensesRequestDTO.getEndAt());
        license.setStatus(status);

        return Mapper.toResponseDTO(repo.save(license));


    }

    @Override
    public Void deleteLicense(String idLicense) {
        Licenses license = repo.findByIdLicense(idLicense)
                .orElseThrow(() -> new RuntimeException("Licencia no encontrada"));

        repo.delete(license);
        return null;
    }
}
