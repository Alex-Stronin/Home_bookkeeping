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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EditingCategoriesControllerTest {
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
    void test_view_page_UpdateRecordsDb() throws Exception {
        String url = "/recordsDb/update";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("p")));
    }

    @Test
    @WithMockUser
    void test_editingCategories() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(new TestAuth());
        String url = "/recordsDb/update";

        this.mockMvc.perform(post(url).with(csrf())
                        .param("whatToAdd", "расходы")
                        .param("altNameCategory", "коммунальные платежи")
                        .param("newNameCategory", "абонемент в тренажерный зал"))
                .andExpect(model().size(3))
                .andExpect(status().isOk());

        this.mockMvc.perform(post(url).with(csrf())
                        .param("whatToAdd", "расходы")
                        .param("altNameCategory", "абонемент в тренажерный зал")
                        .param("newNameCategory", "коммунальные платежи"))
                .andExpect(model().size(3))
                .andExpect(status().isOk());
    }
}
