package soft.rodi38.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soft.rodi38.eventorganizer.model.entity.Attendee;

import java.util.UUID;

public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {
}
