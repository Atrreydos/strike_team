package ru.vigovskiy.strike_team.web.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.service.UserService;

import java.util.List;

public class AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserService service;

    @Autowired
    public AbstractUserController(UserService service) {
        this.service = service;
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User getByLogin(String login) {
        log.info("getByLogin {}", login);
        return service.getByLogin(login);
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User create(User user) {
        log.info("create {}", user);
        return service.create(user);
    }

    public void update(User user) {
        log.info("update {}", user);
        service.update(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }
}