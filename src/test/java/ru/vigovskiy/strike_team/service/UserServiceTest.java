package ru.vigovskiy.strike_team.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void get() {
        User user = userService.get(USER1_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        userService.get(0);
    }

    @Test
    public void getByLogin() {
        User user = userService.getByLogin("user1_login");
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes");
    }

    @Test(expected = NotFoundException.class)
    public void getByLoginNotFound() {
        userService.getByLogin("login");
    }

    @Test
    public void getAll() {
        List<User> users = userService.getAll();
        assertThat(users).isEqualTo(Arrays.asList(ADMIN_1, USER_1));
    }

    @Test
    public void create() {
        User newUser = new User(null, "name", "login", "password");
        User createdUser = userService.create(newUser);
        newUser.setId(createdUser.getId());
        assertThat(newUser).isEqualToComparingFieldByField(createdUser);
        assertThat(userService.getAll()).isEqualTo(Arrays.asList(ADMIN_1, newUser, USER_1));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void duplicateLoginCreate() {
        User newUser = new User(null, "name", "user1_login", "password");
        userService.create(newUser);
    }

    @Test
    public void update() {
        User updatedUser = userService.get(USER1_ID);
        updatedUser.setLogin("new_login");
        updatedUser.setName("new_name");
        updatedUser.setPassword("new_password");
        userService.update(updatedUser);
        assertThat(updatedUser).isEqualTo(userService.get(USER1_ID));
    }

    @Test
    public void delete() {
        userService.delete(USER1_ID);
        assertThat(userService.getAll()).isEqualTo(Collections.singletonList(ADMIN_1));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        userService.delete(0);
    }
}