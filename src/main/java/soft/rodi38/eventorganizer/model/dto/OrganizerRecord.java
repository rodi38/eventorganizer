package soft.rodi38.eventorganizer.model.dto;

import java.util.List;
import java.util.UUID;

public record OrganizerRecord(UUID id, String name, String email, List<EventRecord> events) {
}
