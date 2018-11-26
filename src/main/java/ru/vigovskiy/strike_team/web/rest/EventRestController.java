package ru.vigovskiy.strike_team.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.EventDto;
import ru.vigovskiy.strike_team.service.EventService;

import java.util.List;

@RestController
@RequestMapping(EventRestController.REST_URL)
public class EventRestController {
    static final String REST_URL = "/rest/events";

    private final EventService service;

    @Autowired
    public EventRestController(EventService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public EventDto get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping
    public List<EventDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public EventDto create(@RequestBody EventDto eventDto) {
        return service.create(eventDto);
    }

    @PutMapping
    public void update(@RequestBody EventDto eventDto) {
        service.update(eventDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
