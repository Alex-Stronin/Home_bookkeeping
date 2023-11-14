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
public class CalculationIncomesControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    void test_viewPageIncome() throws Exception {
        String url = "/income/calculation";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    void test_controller_calculation_Incomes_for_family() throws Exception {
        String url = "/income/calculation";
        SecurityContextHolder.getContext().setAuthentication(new TestAuth());
        String checkbox = "family";
        String startPeriod = String.valueOf(LocalDate.of(2023, 9, 1));
        String endPeriod = String.valueOf(LocalDate.of(2023, 12, 31));

        this.mockMvc.perform(post(url).with(csrf())
                        .param("checkbox", checkbox)
                        .param("startDate", startPeriod)
                        .param("endDate", endPeriod))
                .andExpect(model().size(2))
                .andExpect(model().attribute("totalIncomeFamily", 466750))
                .andExpect(status().isOk());
    }

    @Test
    void test_controller_calculation_Incomes_for_user() throws Exception {
        String url = "/income/calculation";

        SecurityContextHolder.getContext().setAuthentication(new TestAuth());
        String checkbox = "user";
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);

        this.mockMvc.perform(post(url).with(csrf())
                        .param("checkbox", checkbox)
                        .param("startDate", String.valueOf(startPeriod))
                        .param("endDate", String.valueOf(endPeriod)))
                .andExpect(model().size(2))
                .andExpect(model().attribute("totalIncomeUser", 100250))
                .andExpect(status().isOk());
    }
}
