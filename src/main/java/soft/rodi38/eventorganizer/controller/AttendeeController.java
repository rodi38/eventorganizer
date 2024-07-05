package soft.rodi38.eventorganizer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.rodi38.eventorganizer.model.dto.AttendeeResponse;
import soft.rodi38.eventorganizer.model.dto.request.CreateAttendeeRequest;
import soft.rodi38.eventorganizer.service.AttendeeService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/attendees")
public class AttendeeController {

    private AttendeeService attendeeService;


    @GetMapping
    public ResponseEntity<List<AttendeeResponse>> findAll() {
        List<AttendeeResponse> response = attendeeService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AttendeeResponse> findById(@PathVariable UUID id) {
        AttendeeResponse response = attendeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody AttendeeResponse request) {
        attendeeService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        attendeeService.delete(id);
    }





}
