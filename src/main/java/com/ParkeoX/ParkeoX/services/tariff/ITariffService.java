package com.ParkeoX.ParkeoX.services.tariff;

import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffResponseDTO;

import java.util.List;

public interface ITariffService {

    List<TariffResponseDTO> findAll();
    TariffRequestDTO CreateTariff(TariffRequestDTO tariffRequestDTO);
    TariffRequestDTO UpdateTariff(Long id, TariffRequestDTO tariffRequestDTO);
    Void DeleteTariff(Long id);


}
