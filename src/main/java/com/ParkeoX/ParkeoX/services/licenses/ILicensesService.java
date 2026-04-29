package com.ParkeoX.ParkeoX.services.licenses;

import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesResponseDTO;

import java.util.List;

public interface ILicensesService {

    List<LicensesResponseDTO> findAll();
    LicensesResponseDTO findById(String idLicense);
    LicensesRequestDTO createLicense(LicensesRequestDTO LicensesRequestDTO);
    LicensesResponseDTO updateLicense(String idLicense, LicensesRequestDTO requestDTO);
    Void deleteLicense(String idLicense);
}
