package soft.rodi38.eventorganizer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.mapper.EventMapper;
import soft.rodi38.eventorganizer.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    public List<EventRecord> findAllEvents() {
        return EventMapper.INSTANCE.eventsToEventRecords(eventRepository.findAll());
    }


}
