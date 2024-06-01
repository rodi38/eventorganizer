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
import soft.rodi38.eventorganizer.exception.attendee.AttendeeNotFoundException;
import soft.rodi38.eventorganizer.model.dto.AttendeeRecord;
import soft.rodi38.eventorganizer.model.dto.request.CreateAttendeeRequest;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.service.AttendeeService;

import java.util.UUID;

@WebMvcTest(AttendeeController.class)
@AutoConfigureMockMvc
public class AttendeeControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AttendeeService attendeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Attendee attendee;
    private AttendeeRecord attendeeRecord;
    private CreateAttendeeRequest createAttendeeRequest;
    @BeforeEach
    void setUp() {
        createAttendeeRequest = new CreateAttendeeRequest("Beatrice", "beato2battler@email.com");
        UUID attendeeUUID = UUID.randomUUID();

        attendee = new Attendee();
        attendee.setId(attendeeUUID);
        attendee.setName(createAttendeeRequest.name());
        attendee.setEmail(createAttendeeRequest.email());
        attendeeRecord = new AttendeeRecord(attendeeUUID, attendee.getName(), attendee.getEmail(), null);



    }

    @Test
    @WithMockUser
    void shouldFindAllAttendees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/attendees")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser
    void shouldCreateAttendee() throws Exception {
        Mockito.when(attendeeService.create(Mockito.any(CreateAttendeeRequest.class))).thenReturn(attendeeRecord);
        mockMvc.perform(MockMvcRequestBuilders.post("/attendees")
               .with(SecurityMockMvcRequestPostProcessors.csrf())
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(createAttendeeRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(attendee.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(attendee.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(attendee.getEmail()));

    }

    @Test
    @WithMockUser
    void shouldFindById() throws Exception {
        Mockito.when(attendeeService.findById(Mockito.any(UUID.class))).thenReturn(attendeeRecord);
        mockMvc.perform(MockMvcRequestBuilders.get("/attendees/{id}", attendee.getId())
               .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON))
//                .content(objectMapper.writeValueAsString(UUID.randomUUID())))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(attendee.getId().toString()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(attendee.getName()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(attendee.getEmail()));
    }

    @Test
    @WithMockUser
    void shouldFindByIdReturnNotFoundWhenAttendeeNotExists() throws Exception {
        Mockito.when(attendeeService.findById(Mockito.any(UUID.class)))
                .thenThrow(new AttendeeNotFoundException("Attendee not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/attendees/{id}", UUID.randomUUID())
               .with(SecurityMockMvcRequestPostProcessors.csrf())
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldUpdateAttendee() throws Exception {
        Mockito.when(attendeeService.update(Mockito.any(AttendeeRecord.class))).thenReturn(attendeeRecord);
        Mockito.when(attendeeService.findById(attendee.getId())).thenReturn(attendeeRecord);


        mockMvc.perform(MockMvcRequestBuilders.put("/attendees")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attendeeRecord)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());


        mockMvc.perform(MockMvcRequestBuilders.get("/attendees/{id}", attendee.getId())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(attendee.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(attendee.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(attendee.getEmail()));


    }








}
