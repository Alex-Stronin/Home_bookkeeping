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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RemoveCategoriesControllerTest {

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
    void test_view_page_remove_records_in_Db() throws Exception {
        String url = "/recordsDb/remove";
        this.mockMvc.perform(get(url).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("p")));
    }

    @Test
    @WithMockUser
    @Disabled
    void test_remove_records_from_Db() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(new TestAuth());

        String url = "/recordsDb/remove";
        String whatRemove = "расходы";
        String nameRemoveCategory = "коммунальные платежи";
        this.mockMvc.perform(post(url).with(csrf())
                        .param("whatRemove", whatRemove)
                        .param("nameRemoveCategory", nameRemoveCategory))
                .andExpect(model().size(3))
                .andExpect(status().isOk());
    }
}
