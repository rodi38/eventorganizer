package soft.rodi38.eventorganizer.model.entity;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEventCreation() {

        UUID eventId = UUID.randomUUID();
        UUID attendeeId = UUID.randomUUID();
        UUID organizerId = UUID.randomUUID();
        OffsetDateTime startDate = OffsetDateTime.of(
                LocalDateTime.of(2024,05, 1, 2, 02,4,5 ),
                ZoneOffset.of("-3"));

        OffsetDateTime endDate = OffsetDateTime.of(
                LocalDateTime.of(2024,05, 5, 2, 02,4,5 ),
                ZoneOffset.of("-3"));

        Event event = new Event(eventId, "Evento de tecnologia","Fortaleza",Instant.now(), startDate, endDate, new ArrayList<>(), null );
//        Event event = Event.builder().id(eventId).name("Evento de tecnologia").startDate(startDate).endDate(endDate).attendees(new ArrayList<>()).organizer(null).build();
        Attendee attendee = new Attendee(attendeeId, "Rodrigo","rodrigo@email.com", "1234", Instant.now(), List.of(event));
        Organizer organizer = new Organizer(organizerId,"Empresario","empresario@email.com", "1234", Instant.now(), List.of(event) );

        event.getAttendees().add(attendee);
        event.setOrganizer(organizer);


        assertNotNull(event);
        assertEquals(eventId, event.getId());
        assertEquals("Evento de tecnologia", event.getName());
        assertEquals("Fortaleza", event.getLocation());
        assertEquals(startDate, event.getStartDate());
        assertEquals(attendee, event.getAttendees().iterator().next());
        assertEquals(organizer, event.getOrganizer());

    }
}
