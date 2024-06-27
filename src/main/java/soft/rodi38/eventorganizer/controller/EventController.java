package soft.rodi38.eventorganizer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateEventRequest;
import soft.rodi38.eventorganizer.service.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

    private EventService eventService;

    @GetMapping()
    public ResponseEntity<List<EventRecord>> findAll() {
        List<EventRecord> events = eventService.findAllEvents();
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @PostMapping()
    public ResponseEntity<EventRecord> create(@RequestBody CreateEventRequest eventRecord) {
        EventRecord response = eventService.create(eventRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventRecord> findById(@PathVariable UUID id) {
        EventRecord response = eventService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody EventRecord request) {
        eventService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        eventService.delete(id);
    }

}
