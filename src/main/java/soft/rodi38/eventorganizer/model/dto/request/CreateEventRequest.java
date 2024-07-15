package soft.rodi38.eventorganizer.model.dto.request;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateEventRequest(String name, String address, int maxQuantityAttendee, OffsetDateTime startDate, OffsetDateTime endDate, UUID organizerId) {
}
