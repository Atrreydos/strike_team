package ru.vigovskiy.strike_team.web.rest.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.UserDto;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.StringJoiner;

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
    public ResponseEntity create(@Valid UserDto dto, BindingResult result) {
        if (result.hasErrors()) {
            StringJoiner joiner = new StringJoiner("<br>");
            result.getFieldErrors().forEach(
                    fe -> {
                        String msg = fe.getDefaultMessage();
                        if (!msg.startsWith(fe.getField())) {
                            msg = fe.getField() + ' ' + msg;
                        }
                        joiner.add(msg);
                    });
            return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(super.create(dto), HttpStatus.OK);
    }

    @Override
    @PutMapping
    public void update(@RequestBody UserDto dto) {
        super.update(dto);
    }

    @Override
    @PostMapping("/{id}/enabled")
    public void setEnabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.setEnabled(id, enabled);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
