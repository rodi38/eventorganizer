package soft.rodi38.eventorganizer.service;


import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;
import soft.rodi38.eventorganizer.repository.OrganizerRepository;

import java.util.List;

@Service
public class OrganizerService {

    private OrganizerRepository organizerRepository;

    public List<OrganizerRecord> findAll() {
        return null;
    }

    public OrganizerRecord create(CreateOrganizerRequest createOrganizerRequest) {
        return null;
    }
}
