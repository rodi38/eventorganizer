package soft.rodi38.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soft.rodi38.eventorganizer.model.entity.Donation;

import java.util.UUID;

public interface DonationRepository extends JpaRepository<Donation, UUID> {
}
