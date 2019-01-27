package ru.vigovskiy.strike_team.web.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static ru.vigovskiy.strike_team.util.ValidationUtil.getErrorResponse;

@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/admin/users";

    public AdminRestController(UserService service) {
        super(service);
    }

    @GetMapping(value = "/{id}")
    public UserDto get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @GetMapping(value = "by-login/{login}")
    public UserDto getByLogin(@PathVariable("login") String login) {
        log.info("getByLogin {}", login);
        return service.getByLogin(login);
    }

    @GetMapping
    public List<UserDto> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody UserDto dto, BindingResult result) {
        log.info("create {}", dto);
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        UserDto userDto = service.create(dto);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /*TODO не полуается сделать AJAX запрос через PUT*/
    @Override
    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserDto dto) {
        super.update(dto);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PostMapping("/{id}/enabled")
    public void setEnabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.setEnabled(id, enabled);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PostMapping("/{id}/admin")
    public void setAdmin(@PathVariable("id") int id, @RequestParam("admin") boolean admin) {
        log.info(admin ? "make admin {}" : "make not admin {}", id);
        service.setAdmin(id, admin);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        service.delete(id);
    }
}
