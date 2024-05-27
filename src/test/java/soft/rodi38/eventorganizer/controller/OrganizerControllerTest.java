package soft.rodi38.eventorganizer.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;
import soft.rodi38.eventorganizer.model.entity.Organizer;

import java.util.UUID;

@WebMvcTest(OrganizerController.class)
@AutoConfigureMockMvc
public class OrganizerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private Organizer organizer;
    private CreateOrganizerRequest request;
    private OrganizerRecord response;

    @BeforeEach
    void setUp() {
        UUID organizerId = UUID.randomUUID();
        organizer.setId(organizerId);


        request = new CreateOrganizerRequest("Rodrigo", "Rodrigo@email.com");

        organizer.setName(request.name());
        organizer.setEmail(request.email());


        response = new OrganizerRecord(organizerId, request.name(), request.name(), null);

    }


    @Test
    @WithMockUser
    void shoudFindAllOrganizers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/organizers").with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "default")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @WithMockUser
    void shoudCreateOrganizer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/organizers")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(organizer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(organizer.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(organizer.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.events").value(organizer.getEvents()));

    }


}
