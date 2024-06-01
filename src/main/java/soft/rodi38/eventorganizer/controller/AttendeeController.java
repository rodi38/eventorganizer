package soft.rodi38.eventorganizer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateAttendeeRequest;
import soft.rodi38.eventorganizer.service.AttendeeService;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<AttendeeRecord> create(@RequestBody CreateAttendeeRequest request) {
        AttendeeRecord response = attendeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AttendeeRecord> findById(@PathVariable UUID id) {
        AttendeeRecord response = attendeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody AttendeeRecord request) {
        attendeeService.update(request);
    }





}
