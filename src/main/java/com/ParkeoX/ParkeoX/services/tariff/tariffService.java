package com.ParkeoX.ParkeoX.services.tariff;


import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffResponseDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Company;
import com.ParkeoX.ParkeoX.models.Tariff;
import com.ParkeoX.ParkeoX.models.VehicleType;
import com.ParkeoX.ParkeoX.repository.companyRepository.CompanyRepository;
import com.ParkeoX.ParkeoX.repository.tariffRepository.TariffRepository;
import com.ParkeoX.ParkeoX.repository.vehiclesRepository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class tariffService implements ITariffService {

    private final TariffRepository repo;
    private final CompanyRepository companyRepository;
    private final VehicleTypeRepository vehicleTypeRepository;


    @Override
    public List<TariffResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public TariffRequestDTO CreateTariff(TariffRequestDTO tariffRequestDTO) {

        VehicleType type = vehicleTypeRepository.findById(tariffRequestDTO.getVehicleType()).orElseThrow(() -> new NotFoundException("VehicleType not found"));
        Company company =  companyRepository.findById(tariffRequestDTO.getCompany()).orElseThrow(() -> new NotFoundException("Company not found"));

        Tariff  tariff = Tariff.builder()
                .price(tariffRequestDTO.getPrice())
                .active(tariffRequestDTO.getActive())
                .vehicleType(type)
                .company(company)
                .build();

        return  Mapper.toRequestDTO(repo.save(tariff));
    }

    @Override
    public TariffRequestDTO UpdateTariff(Long id, TariffRequestDTO tariffRequestDTO) {
        VehicleType type = vehicleTypeRepository.findById(tariffRequestDTO.getVehicleType()).orElseThrow(() -> new NotFoundException("VehicleType not found"));
        Company company =  companyRepository.findById(tariffRequestDTO.getCompany()).orElseThrow(() -> new NotFoundException("Company not found"));

        Tariff tariff = repo.findById(id).orElseThrow(() -> new NotFoundException("Tariff not found"));

            tariff.setPrice(tariffRequestDTO.getPrice());
            tariff.setActive(tariffRequestDTO.getActive());
            tariff.setVehicleType(type);
            tariff.setCompany(company);


        return  Mapper.toRequestDTO(repo.save(tariff));
    }

    @Override
    public Void DeleteTariff(Long id) {
       repo.deleteById(id);
        return null;
    }
}
