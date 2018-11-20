package ru.vigovskiy.strike_team.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:events";
    }

//    @GetMapping("/events")
//    public String events(Model model) {
//        model.addAttribute("events", eventService.getAll());
//        return "events";
//    }
}
