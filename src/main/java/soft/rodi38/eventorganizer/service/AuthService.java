package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.exception.email.EmailNotFoundException;
import soft.rodi38.eventorganizer.exception.role.RoleNotFoundException;
import soft.rodi38.eventorganizer.exception.username.UserNameNotFoundException;
import soft.rodi38.eventorganizer.model.dto.request.SignupRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.enums.ERole;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.model.entity.Role;
import soft.rodi38.eventorganizer.repository.AttendeeRepository;
import soft.rodi38.eventorganizer.repository.OrganizerRepository;
import soft.rodi38.eventorganizer.repository.RoleRepository;

@Service
@AllArgsConstructor
public class AuthService {

    OrganizerRepository organizerRepository;
    AttendeeRepository attendeeRepository;

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;



    public void login(){

    }



    public void register(SignupRequest signUpRequest) {
        if (organizerRepository.existsByUsername(signUpRequest.username()) || attendeeRepository.existsByUsername(signUpRequest.username())) {
            throw new UserNameNotFoundException("Username is already taken!");
        } else if (organizerRepository.existsByEmail(signUpRequest.email()) || attendeeRepository.existsByEmail(signUpRequest.email())) {
            throw new EmailNotFoundException("Email is already in use!");
        }

        Role role = findAndSendRole(signUpRequest.role());

        if (role.getName().equals(ERole.ROLE_ORGANIZER)) {
            Organizer organizer = Organizer.builder()
                    .name(signUpRequest.name())
                    .username(signUpRequest.username())
                    .role(role)
                    .email(signUpRequest.email())
                    .password(passwordEncoder.encode(signUpRequest.password()))
                    .build();
            organizerRepository.save(organizer);
        }
        if (role.getName().equals(ERole.ROLE_ATTENDEE)) {
            Attendee attendee = Attendee.builder()
                    .name(signUpRequest.name())
                    .username(signUpRequest.username())
                    .role(role)
                    .email(signUpRequest.email())
                    .password(passwordEncoder.encode(signUpRequest.password()))
                    .build();
            attendeeRepository.save(attendee);
        }

    }

    public Role findAndSendRole(String strRole) {
        return switch (strRole.toUpperCase()) {
            case "ORGANIZER" -> roleRepository.findByName(ERole.ROLE_ORGANIZER)
                    .orElseThrow(() -> new RoleNotFoundException("Role is not found."));
            case "ATTENDEE" -> roleRepository.findByName(ERole.ROLE_ATTENDEE)
                    .orElseThrow(() -> new RoleNotFoundException("Role is not found."));
            default -> throw new RoleNotFoundException("Role is not found.");
        };
    }
}
