package soft.rodi38.eventorganizer.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(@NotBlank @Size(min = 3, max = 150)String name, @NotBlank @Size(min = 3, max = 20) String username, @Email String email, @NotBlank String role, @NotBlank @Size(min = 6, max = 40) String password) {
}
