package soft.rodi38.eventorganizer.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donation")
public class DonationController {


    @PostMapping
    public ResponseEntity<?> create(ResponseBody request) {


        return null;
    }
}
