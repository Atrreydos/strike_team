package ru.vigovskiy.strike_team.web.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.service.UserService;

public class AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public UserService service;

    @Autowired
    public AbstractUserController(UserService service) {
        this.service = service;
    }

    public void update(UserDto dto) {
        log.info("update {}", dto);
        service.update(dto);
    }

    public UserDto create(UserDto dto) {
        log.info("create {}", dto);
        return service.create(dto);
    }
}
