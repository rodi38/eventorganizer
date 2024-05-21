package soft.rodi38.eventorganizer.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soft.rodi38.eventorganizer.model.dto.EventDTO;
import soft.rodi38.eventorganizer.service.EventService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/events")
public class EventController {

    private final EventService eventService;
    @GetMapping()
    public ResponseEntity<List<EventDTO>> findAll() {
        List<EventDTO> response = eventService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
