package soft.rodi38.eventorganizer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrganizerController {

    private OrganizerService organizerService;


    public ResponseEntity<List<OrganizerRecord>> findAll(){

        return null;
    }

    public ResponseEntity<OrganizerRecord> create(@RequestBody CreateOrganizerRequest request) {
        return null;
    }


}
