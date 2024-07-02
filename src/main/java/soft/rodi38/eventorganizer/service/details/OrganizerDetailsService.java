package soft.rodi38.eventorganizer.service.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.repository.OrganizerRepository;
import soft.rodi38.eventorganizer.security.UserDetailsImpl;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrganizerDetailsService implements UserDetailsService {

    private OrganizerRepository organizerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Organizer organizer = organizerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserDetailsImpl.build(organizer);
    }
}
