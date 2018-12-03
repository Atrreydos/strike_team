package ru.vigovskiy.strike_team.web;


import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.vigovskiy.strike_team.TestUtil.userAuth;
import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void testUsersUnAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void testEventsUnAuth() throws Exception {
        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void testUsers() throws Exception {
        mockMvc.perform(get("/users")
                .with(userAuth(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    void testEvents() throws Exception {
        mockMvc.perform(get("/events")
                .with(userAuth(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/events.jsp"));
    }
}