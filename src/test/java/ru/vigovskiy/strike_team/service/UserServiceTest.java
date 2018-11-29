package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.dto.UserDto;
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

class UserServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private UserService service;

    @Test
    void get() {
        User user = service.get(USER1_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getByLogin() {
        User user = service.getByLogin("user1_login");
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes");
    }

    @Test
    void getByLoginNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByLogin("login"));
    }

    @Test
    void getAll() {
        List<User> users = service.getAll();
        assertThat(users).usingElementComparatorIgnoringFields("votes").isEqualTo(USERS);
    }

    @Test
    void create() {
        User newUser = new User(null, "name", "login", "password");
        User createdUser = service.create(new UserDto(newUser));
        newUser.setId(createdUser.getId());
        assertThat(newUser).isEqualToComparingFieldByField(createdUser);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("votes").isEqualTo(Arrays.asList(ADMIN_1, newUser, USER_1));
    }

    @Test
    void duplicateLoginCreate() {
        User newUser = new User(null, "name", "user1_login", "password");
        assertThrows(DataIntegrityViolationException.class, () -> service.create(new UserDto(newUser)));
    }

    @Test
    void update() {
        User updatedUser = service.get(USER1_ID);
        updatedUser.setLogin("new_login");
        updatedUser.setName("new_name");
        updatedUser.setPassword("new_password");
        service.update(new UserDto(updatedUser));
        assertThat(updatedUser).isEqualToIgnoringGivenFields(service.get(USER1_ID), "votes");
    }

    @Test
    void delete() {
        service.delete(USER1_ID);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("votes").isEqualTo(Collections.singletonList(ADMIN_1));
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