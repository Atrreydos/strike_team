package ru.vigovskiy.strike_team.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vigovskiy.strike_team.EventTestData.EVENT1_ID;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.TestUtil.userHttpBasic;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.util.EventUtil.createDtoFromEvent;
import static ru.vigovskiy.strike_team.web.json.JsonUtil.convertToJson;

class EventRestControllerTest extends AbstractControllerTest {

    @Autowired(required = false)
    protected EventService service;

    private static final String REST_URL = EventRestController.REST_URL + '/';

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + EVENT1_ID)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(createDtoFromEvent(EVENT_1))));
    }

    @Test
    void getAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}