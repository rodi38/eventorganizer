package soft.rodi38.eventorganizer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.exception.event.EventNotFoundException;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateEventRequest;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.mapper.EventMapper;
import soft.rodi38.eventorganizer.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    public List<EventRecord> findAllEvents() {
        return EventMapper.INSTANCE.eventsToEventRecords(eventRepository.findAll());
    }

    public EventRecord create(CreateEventRequest request) {
        Event event = eventRepository.save(EventMapper.INSTANCE.createEventReqToEvent(request));
        Event response = eventRepository.save(event);
        return EventMapper.INSTANCE.eventToEventRecord(response);
    }

    public EventRecord findById(UUID id) {
        return EventMapper.INSTANCE.eventToEventRecord(eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found")));
    }

    public EventRecord update(EventRecord request) {
        Event event = eventRepository.findById(request.id())
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        event.setName(request.name());
        event.setLocation(request.location());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());

        return EventMapper.INSTANCE.eventToEventRecord(event);
    }


}
