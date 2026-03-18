package com.ParkeoX.ParkeoX.services.tickets;


import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsResponseDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.*;
import com.ParkeoX.ParkeoX.repository.paymentsRepository.PaymentsRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
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
    private final PaymentsRepository paymentsRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<TicketsResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public TicketsRequestDTO createTicket(TicketsRequestDTO ticketsRequestDTO) {

        Vehicles vehicle = vehiclesRepository.findById(ticketsRequestDTO.getVehicle()).orElseThrow(() -> new NotFoundException("Vehicle not found"));
        Tariff tariff = tariffRepository.findById(ticketsRequestDTO.getTariff()).orElseThrow(() -> new NotFoundException("Tariff not found"));
        Status status =  statusRepository.findById(ticketsRequestDTO.getStatus()).orElseThrow(() -> new NotFoundException("Status not found"));


      Tickets ticket = Tickets.builder()
              .id(ticketsRequestDTO.getId())
              .vehicle(vehicle)
              .checkInAt(ticketsRequestDTO.getCheckInAt())
              .checkOutAt(ticketsRequestDTO.getCheckOutAt())
              .tariff(tariff)
              .status(status)
              .total(ticketsRequestDTO.getTotal())
              .build();


        return Mapper.toRequestDTO(repo.save(ticket));
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
