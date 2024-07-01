package soft.rodi38.eventorganizer.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import soft.rodi38.eventorganizer.exception.event.EventNotFoundException;
import soft.rodi38.eventorganizer.exception.organizer.OrganizerNotFoundException;
import soft.rodi38.eventorganizer.model.dto.OrganizerRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateEventRequest;
import soft.rodi38.eventorganizer.model.dto.request.CreateOrganizerRequest;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.service.OrganizerService;

import java.util.UUID;

@WebMvcTest(OrganizerController.class)
@AutoConfigureMockMvc
public class OrganizerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizerService organizerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Organizer organizer;
    private CreateOrganizerRequest request;
    private OrganizerRecord response;

    @BeforeEach
    void setUp() {
        UUID organizerId = UUID.randomUUID();
        organizer = new Organizer();
        organizer.setId(organizerId);
        request = new CreateOrganizerRequest("Rodrigo", "Rodrigo@email.com", "1234");

        organizer.setName(request.name());
        organizer.setEmail(request.email());


        response = new OrganizerRecord(organizerId, request.name(), request.email(), null);

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
        Mockito.when(organizerService.create(Mockito.any(CreateOrganizerRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/organizers")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(response.id().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(response.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(response.email()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.events").value(organizer.getEvents()));

    }


    @Test
    @WithMockUser
    void shouldFindById() throws Exception {
        Mockito.when(organizerService.findById(Mockito.any(UUID.class))).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/organizers/{id}", organizer.getId())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UUID.randomUUID())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(response.id().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(response.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(response.email()));
    }



    @Test
    @WithMockUser
    void shouldFindByIdReturnNotFoundWhenEventNotExists() throws Exception {
        Mockito.when(organizerService.findById(Mockito.any(UUID.class)))
                .thenThrow(new OrganizerNotFoundException("Organizer not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/events/{id}", UUID.randomUUID())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
