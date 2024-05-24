package soft.rodi38.eventorganizer.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
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
}
