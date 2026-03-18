package com.ParkeoX.ParkeoX.controllers.tickets;


import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsResponseDTO;
import com.ParkeoX.ParkeoX.services.tickets.ITicketService;
import com.ParkeoX.ParkeoX.services.tickets.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("advanced/ticket")
@RequiredArgsConstructor
public class TicketsController {


    private final ITicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketsResponseDTO>> getAll() {
        return ResponseEntity.ok().body(ticketService.findAll());
    }


    @PostMapping
    public ResponseEntity<TicketsRequestDTO> create(@RequestBody TicketsRequestDTO ticketsRequestDTO) {
        TicketsRequestDTO ticket = ticketService.createTicket(ticketsRequestDTO);
        return ResponseEntity.created(URI.create("/tickets")).body(ticket);
    }


    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketsRequestDTO>  update(@PathVariable Long ticketId, @RequestBody TicketsRequestDTO ticketsRequestDTO) {
        TicketsRequestDTO ticket = ticketService.updateTicket(ticketId, ticketsRequestDTO);
        return ResponseEntity.ok().body(ticket);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<TicketsRequestDTO>  delete(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
     }

}
