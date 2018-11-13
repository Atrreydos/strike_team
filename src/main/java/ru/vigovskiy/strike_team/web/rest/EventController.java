package ru.vigovskiy.strike_team.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.service.EventService;

import java.util.List;

@Controller
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    private EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    public Event get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<Event> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Event create(Event event) {
        log.info("create {}", event);
        return service.create(event);
    }

    public void update(Event event) {
        log.info("update {}", event);
        service.update(event);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }
}
