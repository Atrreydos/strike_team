package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.UserTestData.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private UserService userService;

    @Test
    void get() {
        User user = userService.get(USER1_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.get(0));
    }

    @Test
    void getByLogin() {
        User user = userService.getByLogin("user1_login");
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes");
    }

    @Test
    void getByLoginNotFound() {
        assertThrows(NotFoundException.class, () -> userService.getByLogin("login"));
    }

    @Test
    void getAll() {
        List<User> users = userService.getAll();
        assertThat(users).usingElementComparatorIgnoringFields("votes").isEqualTo(USERS);
    }

    @Test
    void create() {
        User newUser = new User(null, "name", "login", "password");
        User createdUser = userService.create(newUser);
        newUser.setId(createdUser.getId());
        assertThat(newUser).isEqualToComparingFieldByField(createdUser);
        assertThat(userService.getAll()).usingElementComparatorIgnoringFields("votes").isEqualTo(Arrays.asList(ADMIN_1, newUser, USER_1));
    }

    @Test
    void duplicateLoginCreate() {
        User newUser = new User(null, "name", "user1_login", "password");
        assertThrows(DataIntegrityViolationException.class, () -> userService.create(newUser));
    }

    @Test
    void update() {
        User updatedUser = userService.get(USER1_ID);
        updatedUser.setLogin("new_login");
        updatedUser.setName("new_name");
        updatedUser.setPassword("new_password");
        userService.update(updatedUser);
        assertThat(updatedUser).isEqualToIgnoringGivenFields(userService.get(USER1_ID), "votes");
    }

    @Test
    void delete() {
        userService.delete(USER1_ID);
        assertThat(userService.getAll()).usingElementComparatorIgnoringFields("votes").isEqualTo(Collections.singletonList(ADMIN_1));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> userService.delete(0));
    }
}