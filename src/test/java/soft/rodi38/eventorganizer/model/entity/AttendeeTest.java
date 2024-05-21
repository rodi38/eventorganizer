package soft.rodi38.eventorganizer.model.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class AttendeeTest {


    UUID attendeeId = UUID.randomUUID();
    UUID eventId = UUID.randomUUID();
    UUID organizerId = UUID.randomUUID();

    Attendee attendee = new Attendee(attendeeId, "Rodrigo", "rodrigo@email.com", new ArrayList<>());
    Event event1 = new Event(eventId, "Evento de tecnologia", "Fortaleza", "2024-08-20", new ArrayList<>(), null);


    @Test
    public void testAttendeeCreation() {

        assertEquals(attendeeId, attendee.getId());
        assertEquals("Rodrigo", attendee.getName());
        assertEquals("rodrigo@email.com", attendee.getEmail());
    }

    @Test
    public void testAttendeeEvent() {

        attendee.getEvents().add(event1);

        assertEquals(attendeeId, attendee.getId());
        assertEquals("Rodrigo", attendee.getName());
        assertEquals("rodrigo@email.com", attendee.getEmail());
        assertEquals(attendee.getEvents().get(0), event1);
    }


}
