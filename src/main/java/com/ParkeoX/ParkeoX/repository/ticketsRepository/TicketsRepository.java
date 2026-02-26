package com.ParkeoX.ParkeoX.repository.ticketsRepository;

import com.ParkeoX.ParkeoX.models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Tickets,Long> {
}
