package soft.rodi38.eventorganizer.model.entity;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEventCreation() {

        UUID eventId = UUID.randomUUID();
        UUID attendeeId = UUID.randomUUID();
        UUID organizerId = UUID.randomUUID();
        Event event = new Event(eventId, "Evento de tecnologia","Fortaleza", "2024-08-20", new HashSet<>(), null );
        Attendee attendee = new Attendee(attendeeId, "Rodrigo","rodrigo@email.com", event);
        Organizer organizer = new Organizer(organizerId,"","", Set.of(event) );

        event.getAttendees().add(attendee);
        event.setOrganizer(organizer);


        assertNotNull(event);
        assertEquals(eventId, event.getId());
        assertEquals("Evento de tecnologia", event.getName());
        assertEquals("Fortaleza", event.getLocation());
        assertEquals("2024-08-20", event.getDate());
        assertEquals(attendee, event.getAttendees().iterator().next());
        assertEquals(organizer, event.getOrganizer());

    }
}
