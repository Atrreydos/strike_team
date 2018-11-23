package ru.vigovskiy.strike_team.web.rest.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.TestUtil;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vigovskiy.strike_team.UserTestData.*;
import static ru.vigovskiy.strike_team.web.json.JsonUtil.convertToJson;


class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.ADMIN_REST_URL + '/';

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(USER_1)));
    }

    @Test
    void getByLogin() throws Exception {
        mockMvc.perform(get(REST_URL + "by/login/" +USER_1.getLogin()))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(USER_1)));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL ))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(USERS)));
    }

    @Test
    void create() throws Exception {
        User expected = new User(null, "new name", "new login", "new password");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expected))))
                .andExpect(status().isOk());

        User returned = TestUtil.readFromJson(action, User.class);
        expected.setId(returned.getId());

        assertThat(returned).isEqualToComparingFieldByField(expected);
        assertThat(userService.getAll()).isEqualTo(Arrays.asList(ADMIN_1, expected, USER_1));
    }

    @Test
    void update() throws Exception {
        User expected = userService.get(USER1_ID);
        expected.setName("updated name");
        expected.setLogin("updated login");
        expected.setPassword("updated Password");
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expected))))
                .andExpect(status().isOk());

        assertThat(userService.get(USER1_ID)).isEqualToIgnoringGivenFields(expected, "votes");
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_ID))
                .andExpect(status().isOk());

        assertThat(userService.getAll()).isEqualTo(Collections.singletonList(ADMIN_1));
    }
}