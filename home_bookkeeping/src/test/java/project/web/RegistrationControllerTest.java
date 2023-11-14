package project.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMapping() throws Exception {
        String url = "/registration";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registration")));
    }


    @Test
    void test_PostMapping() throws Exception {
        String url = "/registration";
        String userName = "John";
        String userLogin = "j-88";
        String userPassword = "fg89{";
        String startingCapitalUser = String.valueOf((new BigDecimal(1568412)));
        String response = this.mockMvc.perform(post(url).with(csrf())
                        .param("name", userName)
                        .param("login", userLogin)
                        .param("password", userPassword)
                        .param("startCapital", startingCapitalUser))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertFalse(response.contains("form"));
    }
}
