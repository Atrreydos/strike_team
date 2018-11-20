package ru.vigovskiy.strike_team.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String events(Model model, @RequestParam(value = "action", required = false) String action
            , @RequestParam(value = "id", required = false) Integer eventId) {
        Event event;
        String result;

        switch (action == null ? "all" : action) {
            case "get":
                event = eventService.getWithEventDays(eventId);
                model.addAttribute("event", event);
                result = "event";
                break;
            case "delete":
                eventService.delete(eventId);
                result = "redirect:events";
                break;
            case "create":
                event = new Event();
                model.addAttribute("event", event);
                result = "eventForm";
                break;
            case "update":
                event = eventService.get(eventId);
                model.addAttribute("event", event);
                result = "eventForm";
                break;
            case "all":
            default:
                model.addAttribute("events", eventService.getAll());
                result = "events";
                break;
        }

        return result;
    }

    @PostMapping
    public String saveEvent(HttpServletRequest request) throws UnsupportedEncodingException {
        Integer id = request.getParameter("id").isEmpty() ? null : getId(request);
        Event event = new Event(id, request.getParameter("name"), request.getParameter("description"));

        if (id == null) {
            eventService.create(event);
        } else {
            eventService.update(event);
        }
        return  "redirect:events";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}
