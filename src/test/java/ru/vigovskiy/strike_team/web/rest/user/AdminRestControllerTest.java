package ru.vigovskiy.strike_team.web.rest.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.TestUtil;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.service.UserService;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.vigovskiy.strike_team.TestUtil.userAuth;
import static ru.vigovskiy.strike_team.UserTestData.*;
import static ru.vigovskiy.strike_team.util.UserUtil.copy;
import static ru.vigovskiy.strike_team.util.UserUtil.createDtoFromUser;
import static ru.vigovskiy.strike_team.web.json.JsonUtil.convertToJson;


class AdminRestControllerTest extends AbstractControllerTest {

    @Autowired(required = false)
    protected UserService service;

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(USER_1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGet() throws Exception {
        User expected = copy(USER_1);
        expected.setPassword("{noop}password");
        mockMvc.perform(get(REST_URL + USER1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(createDtoFromUser(expected))));
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 0)
                .with(userAuth(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getByLogin() throws Exception {
        mockMvc.perform(get(REST_URL + "by/login/" +USER_1.getLogin())
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(USER_1)));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL )
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(USERS)));
    }

    @Test
    void create() throws Exception {
        User newUser = new User(null, "new name", "new login", "new password", false, Role.ROLE_USER, Role.ROLE_ADMIN);
        UserDto expectedDto = createDtoFromUser(newUser);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedDto))))
                .andExpect(status().isOk());

        UserDto returnedDto = TestUtil.readFromJson(action, UserDto.class);
        newUser.setId(returnedDto.getId());
        expectedDto.setId(returnedDto.getId());

        assertThat(returnedDto).isEqualToIgnoringGivenFields(expectedDto, "password");
        assertThat(service.getAll()).isEqualTo(Arrays.asList(ADMIN_1, newUser, USER_1));
    }

    @Test
    void update() throws Exception {
        User user = service.findById(USER1_ID);
        user.setName("updated name");
        user.setLogin("updated login");
        user.setPassword("updated Password");
        user.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        UserDto expectedUser = createDtoFromUser(user);
        mockMvc.perform(put(REST_URL)
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedUser))))
                .andExpect(status().isNoContent());

        UserDto actualUser = service.get(USER1_ID);
        assertThat(actualUser).isEqualToIgnoringGivenFields(expectedUser, "password");
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNoContent());

        assertThat(service.getAll()).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(Collections.singletonList(ADMIN_1));
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 0)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testSetEnabled() throws Exception {
        User user = service.findById(USER1_ID);
        assertThat(user.isEnabled()).isTrue();

        mockMvc.perform(post(REST_URL + USER1_ID + "/enabled")
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .param("enabled", "false"))
                .andExpect(status().isNoContent());

        user = service.findById(USER1_ID);
        assertThat(user.isEnabled()).isFalse();

        mockMvc.perform(post(REST_URL + USER1_ID + "/enabled")
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .param("enabled", "true"))
                .andExpect(status().isNoContent());

        user = service.findById(USER1_ID);
        assertThat(user.isEnabled()).isTrue();
    }

    @Test
    void testSetAdmin() throws Exception {
        User user = service.findById(USER1_ID);
        assertThat(user.isAdmin()).isFalse();

        mockMvc.perform(post(REST_URL + USER1_ID + "/admin")
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .param("admin", "true"))
                .andExpect(status().isNoContent());

        user = service.findById(USER1_ID);
        assertThat(user.isAdmin()).isTrue();

        mockMvc.perform(post(REST_URL + USER1_ID + "/admin")
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .param("admin", "false"))
                .andExpect(status().isNoContent());

        user = service.findById(USER1_ID);
        assertThat(user.isAdmin()).isFalse();
    }

//    @Test
//    void testCreateInvalid() throws Exception {
//        User expected = new User(null, null, "", "newPass", false, Role.ROLE_USER, Role.ROLE_ADMIN);
//        ResultActions action = mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userAuth(ADMIN_1))
//                .content(JsonUtil.convertToJson(expected)))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
//                .andDo(print());
//    }
//
//    @Test
//    void testUpdateInvalid() throws Exception {
//        User updated = service.get(USER1_ID);
//        updated.setName("");
//        mockMvc.perform(put(REST_URL + USER1_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userAuth(ADMIN_1))
//                .content(JsonUtil.convertToJson(updated)))
//                .andExpect(status().isUnprocessableEntity())
//                .andDo(print())
//                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
//                .andDo(print());
//    }
}