package com.ParkeoX.ParkeoX.services.company;


import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyResponseDTO;
import com.ParkeoX.ParkeoX.models.Company;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {

    List<CompanyResponseDTO> findAll();
    CompanyResponseDTO findCompany(String nit);
    CompanyRequestDTO createCompany(CompanyRequestDTO companyRequestDTO);
    CompanyRequestDTO updateCompany(String nit, CompanyRequestDTO companyRequestDTO);
    void deleteCompany(Long id);
}
