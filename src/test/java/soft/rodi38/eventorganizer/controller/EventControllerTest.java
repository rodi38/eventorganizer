package soft.rodi38.eventorganizer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    @MockBean
    private ObjectMapper objectMapper;

    private Event event;
    private EventRecord eventRecord;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setId(UUID.randomUUID());
        event.setName("Evento louco!!!");
        event.setLocation("Fortaleza");
        event.setDate("29/05/2024");
        event.setOrganizer(new Organizer(UUID.randomUUID(), "Rodrigo", "rodrigo@email.com", null ));


        eventRecord = EventMapper.INSTANCE.eventToEventRecord(event);
    }

    @Test
    @WithMockUser()
    void shouldFindAllEvents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events").with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "default")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser()
    void shouldCreateEvent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "default"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventRecord)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(eventRecord.id()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(eventRecord.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value(eventRecord.location()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(eventRecord.date()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.organizer").value(eventRecord.organizerRecord()));
    }


}
