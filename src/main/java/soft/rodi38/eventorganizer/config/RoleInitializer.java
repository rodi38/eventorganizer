package soft.rodi38.eventorganizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft.rodi38.eventorganizer.model.enums.ERole;
import soft.rodi38.eventorganizer.model.entity.Role;
import soft.rodi38.eventorganizer.repository.RoleRepository;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(ERole.ROLE_ORGANIZER).isEmpty()){
            roleRepository.save(Role.builder().name(ERole.ROLE_ORGANIZER).build());
        }

        if (roleRepository.findByName(ERole.ROLE_ATTENDEE).isEmpty()) {
            roleRepository.save(Role.builder().name(ERole.ROLE_ATTENDEE).build());
        }
    }
}
