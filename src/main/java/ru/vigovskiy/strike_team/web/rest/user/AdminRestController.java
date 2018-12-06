package ru.vigovskiy.strike_team.web.rest.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static ru.vigovskiy.strike_team.util.ValidationUtil.getErrorResponse;

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

    /*TODO без @RequestBody не проходят тесты, а с ним не работает на Томкате*/
    @PostMapping
    public ResponseEntity create(@Valid /*@RequestBody*/ UserDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return new ResponseEntity<>(super.create(dto), HttpStatus.OK);
    }

    /*TODO не полуается сделать AJAX запрос через PUT*/
    @Override
    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserDto dto) {
        super.update(dto);
    }

    @Override
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PostMapping("/{id}/enabled")
    public void setEnabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.setEnabled(id, enabled);
    }

    @Override
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
