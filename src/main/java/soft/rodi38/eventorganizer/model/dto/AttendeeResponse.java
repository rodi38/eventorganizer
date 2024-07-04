package soft.rodi38.eventorganizer.model.dto;

import java.util.List;
import java.util.UUID;

public record AttendeeResponse(UUID id, String name, String username, String email, List<EventRecord> events) {
}
