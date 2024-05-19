package soft.rodi38.eventorganizer.model.entity;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendeeTest {

    @Test
    public void testAttendeeCreation() {

        UUID attendeeId = UUID.randomUUID();

        Attendee attendee = new Attendee(attendeeId, "Rodrigo", "rodrigo@email.com", new HashSet<>());
        assertEquals(attendeeId, attendee.getId());
        assertEquals("Rodrigo", attendee.getName());
        assertEquals("rodrigo@email.com", attendee.getEmail());
    }
}
