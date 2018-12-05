package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.vigovskiy.strike_team.UserTestData.*;
import static ru.vigovskiy.strike_team.util.UserUtil.createDtoMinFromUser;

class UserServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private UserService service;

    @Test
    void get() {
        User user = service.get(USER1_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes", "password");
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
        User updatedUser = service.get(USER1_ID);
        updatedUser.setLogin("new_login");
        updatedUser.setName("new_name");
        updatedUser.setPassword("new_password");
        service.update(createDtoMinFromUser(updatedUser));
        assertThat(updatedUser).isEqualToIgnoringGivenFields(service.get(USER1_ID), "votes", "password");
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
        assertTrue(service.get(USER1_ID).isEnabled());
        service.setEnabled(USER1_ID, false);
        assertFalse(service.get(USER1_ID).isEnabled());
    }
}