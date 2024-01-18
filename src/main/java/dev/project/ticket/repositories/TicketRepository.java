package dev.project.ticket.repositories;

import dev.project.ticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Optional<Ticket> findBySerialNumber(String serialNumber);
}
