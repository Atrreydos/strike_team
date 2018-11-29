package ru.vigovskiy.strike_team.web.rest.user;

import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.UserDto;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.service.UserService;

import java.util.List;

import static ru.vigovskiy.strike_team.util.UserUtil.createUserFromDto;

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
    public void create(UserDto dto) {
        User user = createUserFromDto(dto);
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
    @PostMapping("/{id}")
    public void setEnabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.setEnabled(id, enabled);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
