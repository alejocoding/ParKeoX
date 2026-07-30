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
import com.ParkeoX.ParkeoX.repository.usersRepository.UsersRepository;
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
    private final UsersRepository usersRepository;

    @Override
    public List<TicketsResponseDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toResponseDTO).toList();
    }

    @Override
    public TicketsRequestDTO createTicket(TicketsRequestDTO ticketsRequestDTO) {

        Tariff tariff = tariffRepository.findById(ticketsRequestDTO.getTariff()).orElseThrow(() -> new NotFoundException("Tariff not found"));

        Vehicles vehicle = vehiclesRepository
                            .findByPlateNo(ticketsRequestDTO.getVehicle())
                            .orElseGet(()-> {

                                    Vehicles newVehicle = Vehicles.builder()
                                            .plateNo(ticketsRequestDTO.getVehicle())
                                            .vehicleType(tariff.getVehicleType())
                                            .build();
                                    return vehiclesRepository.save(newVehicle);
                            });

        Users user = usersRepository.findByEmail(ticketsRequestDTO.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));


        Status status =  statusRepository.findById(1L).orElseThrow(() -> new NotFoundException("Status not found"));


      Tickets ticket = Tickets.builder()
              .id(ticketsRequestDTO.getId())
              .vehicle(vehicle)
              .checkInAt(ticketsRequestDTO.getCheckInAt())
              .checkOutAt(ticketsRequestDTO.getCheckOutAt())
              .tariff(tariff)
              .status(status)
              .user(user)
              .total(ticketsRequestDTO.getTotal())
              .build();


        return Mapper.toRequestDTO(repo.save(ticket));
    }

    @Override
    public TicketsRequestDTO cerrarTicket(Long id) {
        Tickets ticket = repo.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found"));
        Status status = statusRepository.findById(2L).orElseThrow(() -> new NotFoundException("Status not found"));
        ticket.setStatus(status);

        return Mapper.toRequestDTO(repo.save(ticket));


    }

    @Override
    public TicketsRequestDTO updateTicket(Long id, TicketsRequestDTO ticketsRequestDTO) {

        Tickets ticket = repo.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found"));
        Status status = statusRepository.findById(ticketsRequestDTO.getStatus()).orElseThrow(() -> new NotFoundException("Status not found"));
        Tariff tariff = tariffRepository.findById(ticketsRequestDTO.getTariff()).orElseThrow(() -> new NotFoundException("Tariff not found"));

        ticket.setStatus(status);
        ticket.setTariff(tariff);
        ticket.setCheckInAt(ticketsRequestDTO.getCheckInAt());
        ticket.setCheckOutAt(ticketsRequestDTO.getCheckOutAt());
        ticket.setTotal(ticketsRequestDTO.getTotal());

        return Mapper.toRequestDTO(repo.save(ticket));
    }

    @Override
    public Void deleteTicket(Long id) {

        Tickets ticket = repo.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found to delete"));
        repo.delete(ticket);
        return null;
    }
}
