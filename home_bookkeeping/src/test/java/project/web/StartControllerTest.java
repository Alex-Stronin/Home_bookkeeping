package project.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class StartControllerTest {

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
    void testing_the_initial_request() throws Exception {
        String url = "/";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    void test_go_to_home_page_index() throws Exception {
        String url = "/";

        this.mockMvc.perform(get(url))
                .andExpect(content().string(containsString("index")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Home bookkeeping")));
    }


    @Test
    void test_go_to_page_menu() throws Exception {
        String url = "/menu";
        SecurityContextHolder.getContext().setAuthentication(new TestAuth());

        this.mockMvc.perform(get(url).with(csrf()))
                .andExpect(model().size(1))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Текущий баланс семьи")));
    }

    @Test
    void test_go_to_page_comeIn() throws Exception {
        String url = "/comeIn";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Authorization")));
    }
}

