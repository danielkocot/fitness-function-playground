package de.codecentric.fitnessfunctionplayground;

import de.codecentric.fitnessfunctionplayground.controller.GreetingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGreetingResponseTime() throws Exception {
        long startTime = System.currentTimeMillis();
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
                .andExpect(status().isOk())
                .andReturn();
        long elapsedTime = System.currentTimeMillis() - startTime;
        assertTrue("Response time exceeded", elapsedTime < 200);
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk());
    }
}
