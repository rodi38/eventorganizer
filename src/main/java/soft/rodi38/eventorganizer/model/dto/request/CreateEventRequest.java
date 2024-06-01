package soft.rodi38.eventorganizer.model.dto.request;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateEventRequest(String name, String location, OffsetDateTime startDate, OffsetDateTime endDate, UUID organizerId) {
}
