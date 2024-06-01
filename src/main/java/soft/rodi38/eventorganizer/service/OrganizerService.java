package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.exception.organizer.OrganizerNotFoundException;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.model.mapper.OrganizerMapper;
import soft.rodi38.eventorganizer.repository.OrganizerRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganizerService {

    private OrganizerRepository organizerRepository;

    public List<OrganizerRecord> findAll() {
        return OrganizerMapper.INSTANCE.organizeListToOrganizerRecordList(organizerRepository.findAll());
    }

    public OrganizerRecord create(CreateOrganizerRequest createOrganizerRequest) {
        Organizer organizer = OrganizerMapper.INSTANCE.INSTANCE.createOrganizerRequestToOrganizer(createOrganizerRequest);
        organizerRepository.save(organizer);

        return OrganizerMapper.INSTANCE.organizerToOrganizerRecord(organizer);
    }

    public OrganizerRecord findById(UUID id) {
        return OrganizerMapper.INSTANCE.organizerToOrganizerRecord(organizerRepository.findById(id)
                .orElseThrow(() -> new OrganizerNotFoundException("Organizer not found")));
    }
}
