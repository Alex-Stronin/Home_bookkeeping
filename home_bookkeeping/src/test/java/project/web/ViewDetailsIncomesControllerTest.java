package project.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ViewDetailsIncomesControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()) // enable security for the mock set up
                .build();
        SecurityContextHolder.getContext().setAuthentication(new TestAuth());
    }

    @Test
    void test_view_page_details_income() throws Exception {
        String url = "/details/incomes";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("form")));
    }

    @Test
    void test_controller_view_details_incomes_for_family() throws Exception {
        String url = "/details/incomes";
        String checkbox = "family";
        String startPeriod = String.valueOf(LocalDate.of(2023, 9, 1));
        String endPeriod = String.valueOf(LocalDate.of(2023, 12, 31));

        this.mockMvc.perform(post(url).with(csrf())
                        .param("checkbox", checkbox)
                        .param("startDate", startPeriod)
                        .param("endDate", endPeriod))
                .andExpect(model().size(2))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void test_controller_view_details_incomes_for_user() throws Exception {
        String url = "/details/incomes";
        String checkbox = "user";
        String startPeriod = String.valueOf(LocalDate.of(2023, 9, 1));
        String endPeriod = String.valueOf(LocalDate.of(2023, 12, 31));

        this.mockMvc.perform(post(url).with(csrf())
                        .param("checkbox", checkbox)
                        .param("startDate", startPeriod)
                        .param("endDate", endPeriod))
                .andExpect(model().size(2))
                .andExpect(status().isOk());
    }
}
