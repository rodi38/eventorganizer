package soft.rodi38.eventorganizer.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.AttendeeResponse;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateAttendeeRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendeeMapper {

    AttendeeMapper INSTANCE = Mappers.getMapper(AttendeeMapper.class);
    AttendeeResponse attendeeToAttendeeResponse(Attendee attendee);


    Attendee createAttendeeRequestToAttendee(CreateAttendeeRequest createAttendeeRequest);

    Attendee attendeeResponseToAttendee(AttendeeResponse attendeeRecord);


    List<AttendeeResponse> attendeeListToAttendeeResponseList(List<Attendee> attendees);


    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    EventRecord eventToEventRecord(Event event);





}
