package ru.vigovskiy.strike_team.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vigovskiy.strike_team.model.User;

import java.util.Arrays;

public class UserServiceTest {

    private static ConfigurableApplicationContext appCtx;
    private static UserService userService;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        userService = appCtx.getBean(UserService.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void get() {
        userService.get(10);
    }

    @Test
    public void getByEmail() {
        userService.getByEmail("email");
    }

    @Test
    public void getAll() {
        userService.getAll();
    }

    @Test
    public void create() {
        userService.create(new User());
    }

    @Test
    public void update() {
        userService.update(new User());
    }

    @Test
    public void delete() {
        userService.delete(10);
    }
}