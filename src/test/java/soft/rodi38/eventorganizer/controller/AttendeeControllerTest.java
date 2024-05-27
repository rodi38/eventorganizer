package soft.rodi38.eventorganizer.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import soft.rodi38.eventorganizer.service.AttendeeService;

@WebMvcTest(AttendeeController.class)
@AutoConfigureMockMvc
public class AttendeeControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AttendeeService attendeeService;


    @Test
    @WithMockUser
    void shouldFindAllAttendees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/attendees")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
