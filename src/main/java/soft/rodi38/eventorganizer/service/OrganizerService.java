package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.model.mapper.OrganizerMapper;
import soft.rodi38.eventorganizer.repository.OrganizerRepository;

import java.util.List;

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
}
