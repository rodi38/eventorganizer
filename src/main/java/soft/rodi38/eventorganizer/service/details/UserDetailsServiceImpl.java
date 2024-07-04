package soft.rodi38.eventorganizer.service.details;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.repository.AttendeeRepository;
import soft.rodi38.eventorganizer.repository.OrganizerRepository;
import soft.rodi38.eventorganizer.security.UserDetailsImpl;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AttendeeRepository attendeeRepository;
    private OrganizerRepository organizerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Organizer> organizer = organizerRepository.findByUsername(username);
        if (organizer.isPresent()) {
            return UserDetailsImpl.build(organizer.get());
        }

        Optional<Attendee> attendee = attendeeRepository.findByUsername(username);
        if (attendee.isPresent()) {
            return UserDetailsImpl.build(attendee.get());
        }

        throw new UsernameNotFoundException("User not found");
    }
}
