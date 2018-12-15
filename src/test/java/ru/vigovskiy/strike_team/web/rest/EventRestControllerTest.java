package ru.vigovskiy.strike_team.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.TestUtil;
import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.vigovskiy.strike_team.EventTestData.*;
import static ru.vigovskiy.strike_team.TestUtil.userAuth;
import static ru.vigovskiy.strike_team.UserTestData.*;
import static ru.vigovskiy.strike_team.util.EventUtil.*;
import static ru.vigovskiy.strike_team.web.json.JsonUtil.convertToJson;

class EventRestControllerTest extends AbstractControllerTest {

    @Autowired(required = false)
    protected EventService service;

    private static final String REST_URL = EventRestController.REST_URL + '/';

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + EVENT1_ID)
                .with(userAuth(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(createDtoFromEvent(EVENT_1))));
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 0)
                .with(userAuth(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL )
                .with(userAuth(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(createDtosFromEvents(EVENTS))));
    }

    @Test
    void create() throws Exception {
        Event expected = new Event(null, "new name", "new description");
        EventDto dto = createDtoFromEvent(expected);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(USER_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(dto))))
                .andDo(print())
                .andExpect(status().isOk());

        EventDto returnedDto = TestUtil.readFromJson(action, EventDto.class);
        Event returned = createEventFromDto(returnedDto);
        expected.setId(returned.getId());

        assertThat(returned).isEqualToComparingFieldByField(expected);
        assertThat(service.getAll()).isEqualTo(createDtosFromEvents(Arrays.asList(EVENT_1, EVENT_2, expected)));
    }

    @Test
    void update() throws Exception {
        EventDto expectedDto = service.get(EVENT1_ID);
        expectedDto.setName("updated name");
        expectedDto.setDescription("updated description");
        mockMvc.perform(put(REST_URL)
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedDto))))
                .andExpect(status().isNoContent());

        assertThat(service.get(EVENT1_ID)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + EVENT1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNoContent());

        assertThat(service.getAll()).isEqualTo(Collections.singletonList(createDtoFromEvent(EVENT_2)));
    }

//    @Test
//    void testCreateInvalid() throws Exception {
//        Meal invalid = new Meal(null, null, "Dummy", 200);
//        mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(invalid))
//                .with(userHttpBasic(ADMIN)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
//                .andDo(print());
//    }
//
//    @Test
//    void testUpdateInvalid() throws Exception {
//        Meal invalid = new Meal(MEAL1_ID, null, null, 6000);
//        mockMvc.perform(put(REST_URL + MEAL1_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(invalid))
//                .with(userHttpBasic(USER)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
//                .andDo(print());
//    }
}