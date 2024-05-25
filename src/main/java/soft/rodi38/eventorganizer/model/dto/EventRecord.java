package soft.rodi38.eventorganizer.model.dto;

import java.util.List;

public record EventRecord(String id, String name, String location, String date, List<AttendeeRecord> attendees, OrganizerRecord organizerRecord) {
}
