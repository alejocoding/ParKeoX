package com.ParkeoX.ParkeoX.services.tickets;


import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsResponseDTO;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.repository.tariffRepository.TariffRepository;
import com.ParkeoX.ParkeoX.repository.ticketsRepository.TicketsRepository;
import com.ParkeoX.ParkeoX.repository.vehiclesRepository.VehiclesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {

    private final TicketsRepository repo;
    private final VehiclesRepository vehiclesRepository;
    private final TariffRepository tariffRepository;

    @Override
    public List<TicketsResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::);
    }

    @Override
    public TicketsRequestDTO createTicket(TicketsRequestDTO ticketsRequestDTO) {

        return null;
    }

    @Override
    public TicketsRequestDTO updateTicket(Long id, TicketsRequestDTO ticketsRequestDTO) {
        return null;
    }

    @Override
    public Void deleteTicket(Long id) {
        return null;
    }
}
