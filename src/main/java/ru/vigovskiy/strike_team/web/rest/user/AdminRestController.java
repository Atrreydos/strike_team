package ru.vigovskiy.strike_team.web.rest.user;

import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.service.UserService;

import java.util.List;

@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserController {
    static final String REST_URL = "/rest/admin/users";

    public AdminRestController(UserService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "by/login/{login}")
    public User getByLogin(@PathVariable("login") String login) {
        return super.getByLogin(login);
    }

    @Override
    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void create(@RequestParam("id") Integer id,
                       @RequestParam("name") String name,
                       @RequestParam("login") String login,
                       @RequestParam("password") String password) {
        User user = new User(id, name, login, password);
        if (user.isNew()) {
            super.create(user);
        }
    }

    @Override
    @PutMapping
    public void update(@RequestBody User user) {
        super.update(user);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
