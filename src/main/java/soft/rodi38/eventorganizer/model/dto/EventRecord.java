package soft.rodi38.eventorganizer.model.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record EventRecord(UUID id, String name, String location, OffsetDateTime startDate, OffsetDateTime endDate, List<AttendeeRecord> attendees, OrganizerRecord organizer) {
}
