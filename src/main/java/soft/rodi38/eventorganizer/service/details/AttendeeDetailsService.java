package soft.rodi38.eventorganizer.service.details;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.repository.AttendeeRepository;
import soft.rodi38.eventorganizer.security.UserDetailsImpl;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AttendeeDetailsService implements UserDetailsService {

    private AttendeeRepository attendeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Attendee attendee = attendeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDetailsImpl.build(attendee);
    }
}
