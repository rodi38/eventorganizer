package soft.rodi38.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soft.rodi38.eventorganizer.model.entity.Ticket;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
