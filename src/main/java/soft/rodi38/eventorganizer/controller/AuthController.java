package soft.rodi38.eventorganizer.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soft.rodi38.eventorganizer.model.dto.JwtRecord;
import soft.rodi38.eventorganizer.model.dto.MessageResponse;
import soft.rodi38.eventorganizer.model.dto.request.LoginRequest;
import soft.rodi38.eventorganizer.model.dto.request.SignupRequest;
import soft.rodi38.eventorganizer.repository.RoleRepository;
import soft.rodi38.eventorganizer.security.UserDetailsImpl;
import soft.rodi38.eventorganizer.security.jwt.JwtUtils;
import soft.rodi38.eventorganizer.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtRecord> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtRecord response = authService.login(loginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        authService.register(signupRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("User registered successfully!"));
    }
}
