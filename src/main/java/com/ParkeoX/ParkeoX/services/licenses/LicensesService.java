package com.ParkeoX.ParkeoX.services.licenses;


import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicenseRenewalRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesResponseDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.generator.LicenseGenerator;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.LicenseRenewalRequest;
import com.ParkeoX.ParkeoX.models.LicenseType;
import com.ParkeoX.ParkeoX.models.Licenses;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.models.Users;
import com.ParkeoX.ParkeoX.repository.companyRepository.CompanyRepository;
import com.ParkeoX.ParkeoX.repository.licenseRenewalRepository.LicenseRenewalRequestRepository;
import com.ParkeoX.ParkeoX.repository.licenseTypeRepository.LicenseTypeRepository;
import com.ParkeoX.ParkeoX.repository.licensesRepository.LicenseRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import com.ParkeoX.ParkeoX.repository.usersRepository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LicensesService implements ILicensesService{

    private final LicenseRepository repo;
    private final CompanyRepository companyRepository;
    private final LicenseTypeRepository licenseTypeRepository;
    private final StatusRepository statusRepository;
    private final UsersRepository usersRepository;
    private final LicenseRenewalRequestRepository renewalRequestRepository;

    @Override
    public List<LicensesResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public List<LicensesResponseDTO> findByCompany(String nit) {
        return repo.findByCompanyNit(nit).stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public LicensesResponseDTO findById(String id_license) {
        return repo.findByIdLicense(id_license).map(Mapper::toResponseDTO).orElseThrow(() -> new NotFoundException("Licencia no encontrada"));
    }

    @Override
    public LicensesResponseDTO createLicense(LicensesRequestDTO licensesRequestDTO) {

        Status status = statusRepository.findById(licensesRequestDTO.getStatus())
                .orElseThrow(() -> new NotFoundException("status not found"));
        Company company = companyRepository.findById(licensesRequestDTO.getCompany()).orElseThrow(()-> new NotFoundException("Company not found"));
        LicenseType licenseType = licenseTypeRepository.findById(licensesRequestDTO.getLicenseType()).orElseThrow(()-> new NotFoundException("License Type not found"));

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
                .maxUsers(licensesRequestDTO.getMaxUsers())
                .status(status)
                .build();

        return Mapper.toResponseDTO(repo.save(license));
    }

    @Override
    public LicensesResponseDTO updateLicense(String id, LicensesRequestDTO licensesRequestDTO) {

        Licenses license = repo.findByIdLicense(id)
                .orElseThrow(() -> new NotFoundException("Licencia no encontrada"));

        Status status = statusRepository.findById(licensesRequestDTO.getStatus())
                .orElseThrow(() -> new NotFoundException("status not found"));

        Company company = companyRepository.findById(licensesRequestDTO.getCompany())
                .orElseThrow(()-> new NotFoundException("Company not found"));


        LicenseType licenseType = licenseTypeRepository.findById(licensesRequestDTO.getLicenseType())
                .orElseThrow(()-> new NotFoundException("License Type not found"));



        license.setCompany(company);
        license.setLicenseType(licenseType);
        license.setPrice(licensesRequestDTO.getPrice());
        license.setBeginAt(licensesRequestDTO.getBeginAt());
        license.setEndAt(licensesRequestDTO.getEndAt());
        license.setMaxUsers(licensesRequestDTO.getMaxUsers());
        license.setStatus(status);

        return Mapper.toResponseDTO(repo.save(license));


    }

    @Override
    public void deleteLicense(String idLicense) {
        Licenses license = repo.findByIdLicense(idLicense)
                .orElseThrow(() -> new NotFoundException("Licencia no encontrada"));

        repo.delete(license);
    }

    @Override
    public LicenseRenewalRequestDTO requestRenewal(String idLicense, String requesterEmail) {
        Licenses license = repo.findByIdLicense(idLicense)
                .orElseThrow(() -> new NotFoundException("Licencia no encontrada"));

        Users requester = usersRepository.findByEmail(requesterEmail)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        boolean sameCompany = requester.getCompany() != null && license.getCompany() != null
                && requester.getCompany().getId().equals(license.getCompany().getId());

        if (!sameCompany) {
            throw new AccessDeniedException("No puedes solicitar la renovación de una licencia que no pertenece a tu compañía");
        }

        LicenseRenewalRequest renewalRequest = LicenseRenewalRequest.builder()
                .license(license)
                .requestedBy(requester)
                .resolved(false)
                .build();

        return Mapper.toDTO(renewalRequestRepository.save(renewalRequest));
    }

    @Override
    public List<LicenseRenewalRequestDTO> getRenewalRequests() {
        return renewalRequestRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public LicenseRenewalRequestDTO resolveRenewalRequest(Long id) {
        LicenseRenewalRequest request = renewalRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Solicitud de renovación no encontrada"));

        request.setResolved(true);

        return Mapper.toDTO(renewalRequestRepository.save(request));
    }
}
