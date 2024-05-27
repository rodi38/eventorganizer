package soft.rodi38.eventorganizer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;
import soft.rodi38.eventorganizer.service.OrganizerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/organizers")
public class OrganizerController {

    private OrganizerService organizerService;


    @GetMapping()
    public ResponseEntity<List<OrganizerRecord>> findAll(){
        List<OrganizerRecord> response = organizerService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<OrganizerRecord> create(@RequestBody CreateOrganizerRequest request) {
        OrganizerRecord response = organizerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
