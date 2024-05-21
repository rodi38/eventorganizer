package soft.rodi38.eventorganizer.model.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;


public class OrganizerTest {

    UUID attendeeId = UUID.randomUUID();
    UUID eventId = UUID.randomUUID();
    UUID organizerId = UUID.randomUUID();

    Attendee attendee = new Attendee(attendeeId, "Rodrigo", "rodrigo@email.com", new ArrayList<>());
    Event event1 = new Event(eventId, "Evento de tecnologia", "Fortaleza", "2024-08-20", new ArrayList<>(), null);
    Organizer organizer = new Organizer(organizerId,"Rodrigo","rodrigo@email.com", new ArrayList<>());


    @Test
    public void testOrganizerCreation() {
        assertEquals(organizerId, organizer.getId());
        assertEquals("Rodrigo", organizer.getName());
        assertEquals("rodrigo@email.com", organizer.getEmail());
        assertEquals(new ArrayList<>(), organizer.getEvents());
    }
}
