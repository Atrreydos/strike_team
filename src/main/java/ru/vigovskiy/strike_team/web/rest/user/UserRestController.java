package ru.vigovskiy.strike_team.web.rest.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vigovskiy.strike_team.service.UserService;

@RestController
@RequestMapping(UserRestController.REST_URL)
public class UserRestController extends AbstractUserController {
    static final String REST_URL = "/rest/users";

    public UserRestController(UserService service) {
        super(service);
    }


}
