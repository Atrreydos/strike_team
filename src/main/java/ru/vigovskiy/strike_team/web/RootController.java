package ru.vigovskiy.strike_team.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.service.EventVotingService;
import ru.vigovskiy.strike_team.service.UserService;
import ru.vigovskiy.strike_team.web.rest.user.AbstractUserController;

import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController {

    private EventVotingService eventVotingService;

    @Autowired
    public RootController(UserService service, EventVotingService eventVotingService) {
        super(service);
        this.eventVotingService = eventVotingService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:events";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/events")
    public String events() {
        return "events";
    }

    @GetMapping("/event-votings")
    public String eventVotings() {
        return "event_votings";
    }

    @GetMapping(value = "/event-votings/{eventVotingId}")
    public String eventVoting(@PathVariable("eventVotingId") Integer eventVotingId, ModelMap model) {
        EventVotingDtoFull eventVotingDto = eventVotingService.getWithVoteDays(eventVotingId);
        model.addAttribute("eventVotingDto", eventVotingDto);
        int enabledCount = service.getEnabledCount();
        model.addAttribute("enabledCount", enabledCount);
        return "event_voting";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserDto userTo, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "profile";
        } else {
            userTo.setId(SecurityUtil.authUserId());
            super.update(userTo);
            SecurityUtil.get().update(userTo);
            status.setComplete();
            return "redirect:events";
        }
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserDto userDto, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        } else {
            super.create(userDto);
            status.setComplete();
            return "redirect:login?message=app.registered&username=" + userDto.getLogin();
        }
    }
}
