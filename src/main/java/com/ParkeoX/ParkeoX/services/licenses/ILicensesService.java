package com.ParkeoX.ParkeoX.services.licenses;

import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicenseRenewalRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesResponseDTO;

import java.util.List;

public interface ILicensesService {

    List<LicensesResponseDTO> findAll();
    List<LicensesResponseDTO> findByCompany(String nit);
    LicensesResponseDTO findById(String idLicense);
    LicensesResponseDTO createLicense(LicensesRequestDTO licensesRequestDTO);
    LicensesResponseDTO updateLicense(String idLicense, LicensesRequestDTO requestDTO);
    void deleteLicense(String idLicense);

    LicenseRenewalRequestDTO requestRenewal(String idLicense, String requesterEmail);
    List<LicenseRenewalRequestDTO> getRenewalRequests();
    LicenseRenewalRequestDTO resolveRenewalRequest(Long id);
}
