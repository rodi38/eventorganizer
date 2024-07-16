package soft.rodi38.eventorganizer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.rodi38.eventorganizer.model.dto.TicketResponse;
import soft.rodi38.eventorganizer.model.dto.request.DonationRequest;
import soft.rodi38.eventorganizer.service.DonationService;

@RestController
@RequestMapping("/api/donation")
@AllArgsConstructor
public class DonationController {

    private DonationService donationService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DonationRequest request) {
        TicketResponse response = donationService.create(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
