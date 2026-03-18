package com.ParkeoX.ParkeoX.services.tickets;

import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsResponseDTO;

import java.util.List;

public interface ITicketService {

    List<TicketsResponseDTO> findAll();
    TicketsRequestDTO createTicket(TicketsRequestDTO ticketsRequestDTO);
    TicketsRequestDTO updateTicket(Long id, TicketsRequestDTO ticketsRequestDTO);
    Void deleteTicket(Long id);
}
