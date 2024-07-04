package soft.rodi38.eventorganizer.model.dto;

import java.util.UUID;

public record JwtRecord(String token, String type, UUID id, String username, String email, String Role) {
    public JwtRecord(String token, UUID id, String username, String email, String role) {
        this(token, "Bearer ", id, username, email, role);
    }
}
