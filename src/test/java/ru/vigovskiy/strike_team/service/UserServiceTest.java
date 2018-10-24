package ru.vigovskiy.strike_team.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private UserService userService;

    @Test
    public void get() {
        User user = userService.get(USER1_ID);
        assertThat(user).isEqualToComparingFieldByField(USER_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        userService.get(0);
    }

    @Test
    public void getByLogin() {
        User user = userService.getByLogin("user1_login");
        assertThat(user).isEqualToComparingFieldByField(USER_1);
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

    @Test(expected = DuplicateKeyException.class)
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
        assertThat(updatedUser).isEqualToComparingFieldByField(userService.get(USER1_ID));
    }

    @Test
    public void delete() {
        userService.delete(USER1_ID);
        assertThat(userService.getAll()).isEqualTo(Arrays.asList(ADMIN_1));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        userService.delete(0);
    }
}