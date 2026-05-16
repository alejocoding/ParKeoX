package com.ParkeoX.ParkeoX.services.tariff;

import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffResponseDTO;
import com.ParkeoX.ParkeoX.models.Company;

import java.util.List;

public interface ITariffService {

    List<TariffResponseDTO> findAll();
    List<TariffResponseDTO> findTariffByCompany(String nit);
    TariffRequestDTO CreateTariff(TariffRequestDTO tariffRequestDTO);
    TariffRequestDTO UpdateTariff(Long id, TariffRequestDTO tariffRequestDTO);
    Void DeleteTariff(Long id);


}
