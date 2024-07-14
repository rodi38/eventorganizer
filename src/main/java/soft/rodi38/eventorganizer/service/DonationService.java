package soft.rodi38.eventorganizer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.List;

@Service
@AllArgsConstructor
public class DonationService {

    private TicketRepository ticketRepository;
    private DonationRepository donationRepository;

    @Transactional
    public TicketResponse create(DonationRequest request) {
        Donation donation = DonationMapper.INSTANCE.donationRequestToDonation(request);
        List<Ticket> tickets =  ticketRepository.findAllByEventIdAndHasSoldFalse(request.eventId());

        if (tickets.isEmpty()) {
            throw new IllegalStateException("No available tickets for this event!");
        }

        Ticket ticket = tickets.get(0);

        ticket.setDonationType(request.donationType());

        Attendee attendee = Attendee.builder().id(request.attendeeId()).build();

        ticket.setAttendee(attendee);
        ticket.setHasSold(true);
        donation.setAttendee(attendee);
        donation.setEvent(ticket.getEvent());

        donationRepository.save(donation);
        ticketRepository.save(ticket);

        return TicketMapper.INSTANCE.ticketToTicketResponse(ticket);
    }


}
