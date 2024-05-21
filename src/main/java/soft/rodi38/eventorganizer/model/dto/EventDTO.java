package soft.rodi38.eventorganizer.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {

    private String id;
    private String name;
    private String location;
    private String date;
    private List<AttendeeDTO> attendees;
    private OrganizerDTO organizer;

}
