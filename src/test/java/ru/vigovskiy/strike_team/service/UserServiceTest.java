package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.vigovskiy.strike_team.UserTestData.*;
import static ru.vigovskiy.strike_team.util.UserUtil.createDtoFromUser;
import static ru.vigovskiy.strike_team.util.UserUtil.createDtoMinFromUser;

class UserServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private UserService service;

    @Test
    void findById() {
        User user = service.findById(USER1_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes", "password");
    }

    @Test
    void get() {
        UserDto userDto = service.get(USER1_ID);
        assertThat(userDto).isEqualToIgnoringGivenFields(createDtoFromUser(USER_1), "password");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getByLogin() {
        User user = service.getByLogin("user1_login");
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes", "password");
    }

    @Test
    void getByLoginNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByLogin("login"));
    }

    @Test
    void getAll() {
        List<User> users = service.getAll();
        assertThat(users).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(USERS);
    }

    @Test
    void getEnabledCount() {
        int allCount = service.getEnabledCount();
        assertThat(allCount).isEqualTo(2);
    }

    @Test
    void create() {
        User newUser = new User(null, "name", "login", "password", false, Role.ROLE_USER);
        User createdUser = service.create(createDtoMinFromUser(newUser));
        newUser.setId(createdUser.getId());
        assertThat(newUser).isEqualToIgnoringGivenFields(createdUser, "password");
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(Arrays.asList(ADMIN_1, newUser, USER_1));
    }

    @Test
    void duplicateLoginCreate() {
        User newUser = new User(null, "name", "user1_login", "password", false, Role.ROLE_USER);
        assertThrows(DataIntegrityViolationException.class, () -> service.create(createDtoMinFromUser(newUser)));
    }

    @Test
    void update() {
        User updatedUser = service.findById(USER1_ID);
        updatedUser.setLogin("new_login");
        updatedUser.setName("new_name");
        updatedUser.setPassword("new_password");
        service.update(createDtoMinFromUser(updatedUser));
        assertThat(updatedUser).isEqualToIgnoringGivenFields(service.findById(USER1_ID), "votes", "password");
    }

    @Test
    void delete() {
        service.delete(USER1_ID);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(Collections.singletonList(ADMIN_1));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(0));
    }

    @Test
    void setEnabled() {
        service.setEnabled(USER1_ID, true);
        assertTrue(service.findById(USER1_ID).isEnabled());
        service.setEnabled(USER1_ID, false);
        assertFalse(service.findById(USER1_ID).isEnabled());
    }

    @Test
    void setAdmin() {
        service.setAdmin(USER1_ID, true);
        assertTrue(service.findById(USER1_ID).getRoles().contains(Role.ROLE_ADMIN));
        service.setAdmin(USER1_ID, false);
        assertFalse(service.findById(USER1_ID).getRoles().contains(Role.ROLE_ADMIN));
    }
}