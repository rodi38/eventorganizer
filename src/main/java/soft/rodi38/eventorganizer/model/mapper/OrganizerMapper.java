package soft.rodi38.eventorganizer.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.entity.Organizer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizerMapper {

    OrganizerMapper INSTANCE = Mappers.getMapper(OrganizerMapper.class);
    OrganizerRecord organizerToOrganizerRecord(Organizer organizer);

    Organizer createOrganizerRequestToOrganizer(CreateOrganizerRequest createOrganizerRequest);

    Organizer organizerRecordToOrganizer(OrganizerRecord organizerRecord);


    List<OrganizerRecord> organizeListToOrganizerRecordList(List<Organizer> organizerList);

    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    EventRecord eventToEventRecord(Event event);


}
