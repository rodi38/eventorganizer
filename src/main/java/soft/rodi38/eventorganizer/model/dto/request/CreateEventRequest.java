package soft.rodi38.eventorganizer.model.dto.request;

import java.util.UUID;

public record CreateEventRequest(String name, String location, String date, UUID organizerId) {
}
