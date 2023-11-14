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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CalculationExpensesControllerTest {

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
    void test_viewPageExpense() throws Exception {
        String url = "/expense/calculation";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void test_controller_calculation_Expenses_for_family() throws Exception {
        String url = "/expense/calculation";

        String checkbox = "family";
        String startPeriod = String.valueOf(LocalDate.of(2023, 9, 1));
        String endPeriod = String.valueOf(LocalDate.of(2023, 12, 31));

        this.mockMvc.perform(post(url).with(csrf())
                        .param("checkbox", checkbox)
                        .param("startDate", startPeriod)
                        .param("endDate", endPeriod))
                .andExpect(model().size(2))
                .andExpect(model().attribute("totalExpenseFamily", 163200))
                .andExpect(status().isOk());
    }

    @Test
    void test_controller_calculation_Expenses_for_user() throws Exception {
        String url = "/expense/calculation";

        SecurityContextHolder.getContext().setAuthentication(new TestAuth());
        String checkbox = "user";
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);

        this.mockMvc.perform(post(url).with(csrf())
                        .param("checkbox", checkbox)
                        .param("startDate", String.valueOf(startPeriod))
                        .param("endDate", String.valueOf(endPeriod)))
                .andExpect(model().size(2))
                .andExpect(model().attribute("totalExpenseUser", 32900))
                .andExpect(status().isOk());
    }
}
