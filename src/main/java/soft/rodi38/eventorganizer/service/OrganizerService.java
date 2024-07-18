package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public OrganizerRecord findById(UUID id) {
        return OrganizerMapper.INSTANCE.organizerToOrganizerRecord(organizerRepository.findById(id)
                .orElseThrow(() -> new OrganizerNotFoundException("Organizer not found")));
    }

    public void update(OrganizerRecord request) {
        Organizer organizer = OrganizerMapper.INSTANCE.organizerRecordToOrganizer(findById(request.id()));

        organizer.setEmail(request.email());
        organizer.setName(request.name());
        organizerRepository.save(organizer);

    }

    public void delete(UUID id) {
        if (organizerRepository.existsById(id)) {
            organizerRepository.deleteById(id);
            return;
        }
        throw new OrganizerNotFoundException("Organizer not found with id: " + id);
    }
}
