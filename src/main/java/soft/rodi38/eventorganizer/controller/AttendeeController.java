package soft.rodi38.eventorganizer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.service.AttendeeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/attendees")
public class AttendeeController {

    private AttendeeService attendeeService;


    @GetMapping
    public ResponseEntity<List<AttendeeRecord>> findAll() {
        List<AttendeeRecord> response = attendeeService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
