package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateAttendeeRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.mapper.AttendeeMapper;
import soft.rodi38.eventorganizer.repository.AttendeeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeService {

    private AttendeeRepository attendeeRepository;

    public List<AttendeeRecord> findAll() {
        return AttendeeMapper.INSTANCE.attendeeListToAttendeeRecordList(attendeeRepository.findAll());
    }

    public AttendeeRecord create(CreateAttendeeRequest createAttendeeRequest) {
        Attendee attendee = AttendeeMapper.INSTANCE.INSTANCE.createAttendeeRequestToAttendee(createAttendeeRequest);
        attendeeRepository.save(attendee);
        return  AttendeeMapper.INSTANCE.attendeeToAttendeeRecord(attendee);
    }
}
