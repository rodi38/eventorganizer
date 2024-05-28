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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

        OffsetDateTime startDate = OffsetDateTime.of(
                LocalDateTime.of(2024,05, 1, 2, 02,4,5 ),
                ZoneOffset.of("-3"));

        OffsetDateTime endDate = OffsetDateTime.of(
                LocalDateTime.of(2024,05, 5, 2, 02,4,5 ),
                ZoneOffset.of("-3"));

        event = new Event();
        event.setId(UUID.randomUUID());
        event.setName("Evento louco!!!");
        event.setLocation("Fortaleza");
        event.setCreatedAt(Instant.now());
        event.setStartDate(OffsetDateTime.now().plusDays(5).withNano(0));
        event.setEndDate(OffsetDateTime.now().plusDays(10).withNano(0));
        event.setOrganizer(new Organizer(organizerId, "Rodrigo", "rodrigo@email.com",Instant.now(), null ));

        eventRecord = EventMapper.INSTANCE.eventToEventRecord(event);
        createEventRequest = new CreateEventRequest(event.getName(), event.getLocation(), startDate, endDate, organizerId);
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(eventRecord.startDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value(eventRecord.endDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.organizer.id").value(createEventRequest.organizerId().toString()));
    }


}
