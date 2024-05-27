package soft.rodi38.eventorganizer.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateEventRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.entity.Organizer;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);


    @Mapping(source = "attendees", target = "attendees")
    @Mapping(source = "organizer", target = "organizer")
    EventRecord eventToEventRecord(Event event);

    Event eventRecordToEvent(EventRecord eventRecord);

    @Mapping(target = "organizer.id", source = "organizerId")
    Event createEventReqToEvent(CreateEventRequest createEventRequest);


    List<EventRecord> eventsToEventRecords(List<Event> events);

    @Mapping(target = "events", ignore = true) // Ignore mapping events to avoid circular reference
    AttendeeRecord attendeeToAttendeeRecord(Attendee attendee);

    @Mapping(target = "events", ignore = true) // Ignore mapping events to avoid circular reference
    OrganizerRecord organizerToOrganizerRecord(Organizer organizer);


}
