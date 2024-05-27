package soft.rodi38.eventorganizer.model.dto;

import java.util.List;
import java.util.UUID;

public record EventRecord(UUID id, String name, String location, String date, List<AttendeeRecord> attendees, OrganizerRecord organizer) {
}
