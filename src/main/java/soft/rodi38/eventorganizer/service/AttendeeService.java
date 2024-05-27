package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.repository.AttendeeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeService {

    private AttendeeRepository attendeeRepository;


    public List<AttendeeRecord> findAll() {

        return null;
    }
}
