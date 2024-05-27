package soft.rodi38.eventorganizer.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateAttendeeRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendeeMapper {

    AttendeeMapper INSTANCE = Mappers.getMapper(AttendeeMapper.class);

    AttendeeRecord attendeeToAttendeeRecord(Attendee attendee);

    Attendee createAttendeeRequestToAttendee(CreateAttendeeRequest createAttendeeRequest);


    List<AttendeeRecord> attendeeListToAttendeeRecordList(List<Attendee> attendees);


    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    EventRecord eventToEventRecord(Event event);





}
