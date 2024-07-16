package soft.rodi38.eventorganizer.model.dto.request;


import soft.rodi38.eventorganizer.model.enums.DonationType;

import java.math.BigDecimal;
import java.util.UUID;

public record DonationRequest(DonationType donationType, BigDecimal value, UUID attendeeId, UUID eventId) {
}
