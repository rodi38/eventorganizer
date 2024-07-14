package soft.rodi38.eventorganizer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.dto.TicketResponse;
import soft.rodi38.eventorganizer.model.dto.request.DonationRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Donation;
import soft.rodi38.eventorganizer.model.entity.Ticket;
import soft.rodi38.eventorganizer.model.mapper.DonationMapper;
import soft.rodi38.eventorganizer.model.mapper.TicketMapper;
import soft.rodi38.eventorganizer.repository.DonationRepository;
import soft.rodi38.eventorganizer.repository.TicketRepository;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class DonationService {

    private TicketRepository ticketRepository;
    private DonationRepository donationRepository;

    public TicketResponse create(DonationRequest request) {
        Donation donation = DonationMapper.INSTANCE.donationRequestToDonation(request);
        Ticket ticket = ticketRepository.findAllByEventIdAndHasSoldFalse(request.eventId()).get(0);

        ticket.setDonationType(request.donationType());

        Attendee attendee = Attendee.builder().id(request.attendeeId()).build();

        ticket.setAttendee(attendee);

        ticket.setHasSold(true);

        donationRepository.save(donation);
        ticketRepository.save(ticket);

        return TicketMapper.INSTANCE.ticketToTicketResponse(ticket);
    }


}
