package ua.donetc.EXAMINATOR.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import ua.donetc.EXAMINATOR.entity.Questions;

import java.util.HashMap;
import java.util.Map;

@WebMvcTest(ExamController.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExam() throws Exception {
        // Mock the response from restTemplate
        Questions[] questions = {new Questions(), new Questions()};
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Questions[].class))).thenReturn(questions);

        // Prepare the request body
        Map<String, Integer> spec = new HashMap<>();
        spec.put("example.com", 5);

        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post("/exam")
                        .contentType("application/json")
                        .content("{\"example.com\": 5}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("EXAM"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.section.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.section[0].questions.length()").value(2));
    }
}
