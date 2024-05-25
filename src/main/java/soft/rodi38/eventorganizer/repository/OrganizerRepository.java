package soft.rodi38.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soft.rodi38.eventorganizer.model.entity.Organizer;

import java.util.UUID;

public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {
}
