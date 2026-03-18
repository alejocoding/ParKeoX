package com.ParkeoX.ParkeoX.services.company;


import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyResponseDTO;

import java.util.List;

public interface ICompanyService {

    List<CompanyResponseDTO> findAll();
    CompanyRequestDTO createCompany(CompanyRequestDTO companyRequestDTO);
    CompanyRequestDTO updateCompany(String nit, CompanyRequestDTO companyRequestDTO);
    void deleteCompany(Long id);
}
