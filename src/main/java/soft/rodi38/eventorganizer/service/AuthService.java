package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.exception.email.EmailNotFoundException;
import soft.rodi38.eventorganizer.exception.role.RoleNotFoundException;
import soft.rodi38.eventorganizer.exception.username.UserNameNotFoundException;
import soft.rodi38.eventorganizer.model.dto.JwtRecord;
import soft.rodi38.eventorganizer.model.dto.request.LoginRequest;
import soft.rodi38.eventorganizer.model.dto.request.SignupRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.enums.ERole;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.model.entity.Role;
import soft.rodi38.eventorganizer.repository.AttendeeRepository;
import soft.rodi38.eventorganizer.repository.OrganizerRepository;
import soft.rodi38.eventorganizer.repository.RoleRepository;
import soft.rodi38.eventorganizer.security.UserDetailsImpl;
import soft.rodi38.eventorganizer.security.jwt.JwtUtils;

@Service
@AllArgsConstructor
public class AuthService {

    private OrganizerRepository organizerRepository;
    private AttendeeRepository attendeeRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;


    public JwtRecord login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream().toList().get(0).getAuthority();

        return new JwtRecord(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role);
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
