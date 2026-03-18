package com.ParkeoX.ParkeoX.services.company;

import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyResponseDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.repository.companyRepository.CompanyRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements ICompanyService{


    private final CompanyRepository repo;
    private final StatusRepository statusRepo;


    @Override
    public List<CompanyResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public CompanyRequestDTO createCompany(CompanyRequestDTO companyRequestDTO) {

        Status status = statusRepo.findById(companyRequestDTO.getStatus())
                .orElseThrow(() -> new NotFoundException("Status not found"));


        Company company = Company.builder()
                .name(companyRequestDTO.getName())
                .nit(companyRequestDTO.getNit())
                .address(companyRequestDTO.getAddress())
                .status(status)
                .build();

        return Mapper.toRequestDTO(repo.save(company));
    }

    @Override
    public CompanyRequestDTO updateCompany(String nit, CompanyRequestDTO companyRequestDTO) {

        //If exist
        Company company = repo.findBynit(nit)
                .orElseThrow(() -> new NotFoundException("Company not found"));

        // If Status exist

        Status status = statusRepo.findById(companyRequestDTO.getStatus())
                .orElseThrow(() -> new NotFoundException("Status not found"));


        company.setName(companyRequestDTO.getName());
        company.setAddress(companyRequestDTO.getAddress());
        company.setNit(companyRequestDTO.getNit());
        company.setStatus(status);

        return Mapper.toRequestDTO(repo.save(company));


    }

    @Override
    public void deleteCompany(Long id) {
        if(!repo.existsById(id)){
            throw new NotFoundException("Company not found to delete");
        }
        repo.deleteById(id);

    }
}
