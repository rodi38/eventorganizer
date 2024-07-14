package soft.rodi38.eventorganizer.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.AttendeeResponse;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.DonationRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Donation;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.entity.Organizer;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    DonationMapper INSTANCE = Mappers.getMapper(DonationMapper.class);


    @Mapping(target = "attendee", source = "attendeeId")
    @Mapping(target = "event", source = "eventId")
    Donation donationRequestToDonation(DonationRequest donationRequest);

    @Mapping(target = "events", ignore = true) // Ignore mapping events to avoid circular reference
    Attendee attendeeIdToOrganizer(UUID attendeeID);

    @Mapping(target = "tickets", ignore = true)
    Event eventIdToEvent(UUID eventId);



    @Mapping(target = "events", ignore = true) // Ignore mapping events to avoid circular reference
    Organizer organizerIdToOrganizer(UUID organizerId);

}
