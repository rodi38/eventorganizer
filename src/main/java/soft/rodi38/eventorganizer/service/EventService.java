package soft.rodi38.eventorganizer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soft.rodi38.eventorganizer.exception.event.EventNotFoundException;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateEventRequest;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.entity.Ticket;
import soft.rodi38.eventorganizer.model.mapper.EventMapper;
import soft.rodi38.eventorganizer.repository.EventRepository;
import soft.rodi38.eventorganizer.util.AppUtils;

import java.time.OffsetDateTime;
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

    @Transactional
    public EventRecord create(CreateEventRequest request) {
        Event event = EventMapper.INSTANCE.createEventReqToEvent(request);
        event.setTickets(generateTickets(event));
        Event response = eventRepository.save(event);
        return EventMapper.INSTANCE.eventToEventRecord(response);
    }

    public EventRecord findById(UUID id) {
        return EventMapper.INSTANCE.eventToEventRecord(eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found")));
    }

    public void update(EventRecord request) {
        Event event = EventMapper.INSTANCE.eventRecordToEvent(findById(request.id()));

        event.setName(request.name());
        event.setAddress(request.location());

        eventRepository.save(event);
    }

    public void delete(UUID id) {
        if (eventRepository.existsById(id)){
            eventRepository.deleteById(id);
            return;

        }

        throw new EventNotFoundException("Event not found with id: " + id);
    }

    public String generateTicketCode(String eventName, OffsetDateTime startDate){
        return eventName + startDate + AppUtils.generateRandomString();
    }

    public List<Ticket> generateTickets(Event event){
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < event.getMaxQuantityAttendee(); i++ ) {
            Ticket ticket = Ticket.builder()
                    .code(generateTicketCode(event.getName(), event.getStartDate()))
                    .event(event)
                    .build();

            tickets.add(ticket);
        }
        return tickets;
    }


}
