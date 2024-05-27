package soft.rodi38.eventorganizer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateEventRequest;
import soft.rodi38.eventorganizer.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

    private EventService eventService;

    @GetMapping()
    public ResponseEntity<List<EventRecord>> findAll() {
        List<EventRecord> events = eventService.findAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping()
    public ResponseEntity<EventRecord> create(@RequestBody CreateEventRequest eventRecord) {
        EventRecord response = eventService.create(eventRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
