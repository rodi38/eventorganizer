package soft.rodi38.eventorganizer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import soft.rodi38.eventorganizer.model.dto.EventRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateEventRequest;
import soft.rodi38.eventorganizer.model.entity.Event;
import soft.rodi38.eventorganizer.model.entity.Organizer;
import soft.rodi38.eventorganizer.model.mapper.EventMapper;
import soft.rodi38.eventorganizer.repository.EventRepository;
import soft.rodi38.eventorganizer.service.EventService;

import java.util.UUID;

@WebMvcTest(EventController.class)
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    private Event event;
    private EventRecord eventRecord;
    private CreateEventRequest createEventRequest;

    @BeforeEach
    void setUp() {
        UUID organizerId = UUID.randomUUID();
        event = new Event();
        event.setId(UUID.randomUUID());
        event.setName("Evento louco!!!");
        event.setLocation("Fortaleza");
        event.setDate("29/05/2024");
        event.setOrganizer(new Organizer(organizerId, "Rodrigo", "rodrigo@email.com", null ));

        eventRecord = EventMapper.INSTANCE.eventToEventRecord(event);
        createEventRequest = new CreateEventRequest(event.getName(), event.getLocation(), event.getDate(), organizerId);
    }

    @Test
    @WithMockUser()
    void shouldFindAllEvents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events").with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "default")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldCreateEvent() throws Exception {
        Mockito.when(eventService.create(Mockito.any(CreateEventRequest.class))).thenReturn(eventRecord);


        mockMvc.perform(MockMvcRequestBuilders.post("/events")
//                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "default"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Add CSRF token
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createEventRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(eventRecord.id().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(eventRecord.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value(eventRecord.location()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(eventRecord.date()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.organizerRecord.id").value(createEventRequest.organizerId().toString()));
    }


}
