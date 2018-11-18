package ru.vigovskiy.strike_team.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.service.EventService;

import java.util.List;

@Controller
@RequestMapping("/events/rest")
public class EventRestController {
    private static final Logger log = LoggerFactory.getLogger(EventRestController.class);

    private EventService service;

    @Autowired
    public EventRestController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<Event> getAll() {
        log.info("getAll");
        return service.getAll();
    }
}
