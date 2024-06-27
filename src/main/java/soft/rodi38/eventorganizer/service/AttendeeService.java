package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.exception.attendee.AttendeeNotFoundException;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateAttendeeRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.mapper.AttendeeMapper;
import soft.rodi38.eventorganizer.repository.AttendeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AttendeeService {

    private AttendeeRepository attendeeRepository;

    public List<AttendeeRecord> findAll() {
        return AttendeeMapper.INSTANCE.attendeeListToAttendeeRecordList(attendeeRepository.findAll());
    }

    public AttendeeRecord create(CreateAttendeeRequest request) {
        Attendee attendee = AttendeeMapper.INSTANCE.INSTANCE.createAttendeeRequestToAttendee(request);
        attendeeRepository.save(attendee);
        return  AttendeeMapper.INSTANCE.attendeeToAttendeeRecord(attendee);
    }

    public AttendeeRecord findById(UUID id) {
        return AttendeeMapper.INSTANCE.attendeeToAttendeeRecord(attendeeRepository.findById(id)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee not found")));
    }

    public void update(AttendeeRecord request) {

        Attendee attendee = AttendeeMapper.INSTANCE.attendeeRecordToAttendee(findById(request.id()));

        attendee.setName(request.name());
        attendee.setEmail(request.email());

        attendeeRepository.save(attendee);

    }

    public void delete(UUID id){
        if (attendeeRepository.existsById(id)){
            attendeeRepository.deleteById(id);
            return;
        }

        throw new AttendeeNotFoundException("Attendee not found with id: " + id);

    }
}
