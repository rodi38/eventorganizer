package soft.rodi38.eventorganizer.model.dto.request;

import jakarta.persistence.ManyToOne;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.enums.DonationType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record DonationRequest(DonationType donationType, BigDecimal value, UUID attendeeId, UUID eventId) {
}
