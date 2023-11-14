package project.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AddIncomeTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()) // enable security for the mock set up
                .build();
    }

    @Test
    @WithMockUser
    void test_view() throws Exception {
        String url = "/incomes/add";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("form")));
    }

    @Test
    @Disabled
    void test_add_Income() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(new TestAuth());
        String url = "/incomes/add";
        this.mockMvc.perform(post(url).with(csrf())
                        .param("sourceName", "премия")
                        .param("amount", "6000"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("form")));
    }
}
