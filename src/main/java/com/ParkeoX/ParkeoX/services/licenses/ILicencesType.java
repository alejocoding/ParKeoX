package com.ParkeoX.ParkeoX.services.licenses;


import com.ParkeoX.ParkeoX.DTO.request.licenseTypeDTO.LicenseTypeDTO;
import com.ParkeoX.ParkeoX.models.LicenseType;

import java.util.List;

public interface ILicencesType {


    List<LicenseTypeDTO> getLicensesType();
    LicenseTypeDTO createLicenseType(LicenseTypeDTO licenseTypeDTO);
    LicenseTypeDTO updateLicenseType(Long id, LicenseTypeDTO licenseTypeDTO);
    Void deleteLicenseType(Long id);

}
