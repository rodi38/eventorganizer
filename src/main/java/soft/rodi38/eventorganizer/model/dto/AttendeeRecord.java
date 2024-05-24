package soft.rodi38.eventorganizer.model.dto;

import java.util.List;

public record AttendeeRecord(String id, String name, String email, List<EventRecord> events) {
}
