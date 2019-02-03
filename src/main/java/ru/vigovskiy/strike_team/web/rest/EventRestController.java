package ru.vigovskiy.strike_team.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Enums.EventStatus;
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

    @GetMapping(value = "upcoming")
    public List<EventDto> getAllUpcoming() {
        return service.getAllUpcoming();
    }

    @GetMapping(value = "in-voting")
    public List<EventDto> getAllInVoting() {
        return service.getAllInVoting();
    }

    @PostMapping
    public EventDto create(@RequestBody EventDto eventDto) {
        eventDto.setStatus(EventStatus.UPCOMING_EVENT);
        return service.createOrUpdate(eventDto);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody EventDto eventDto) {
        service.createOrUpdate(eventDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
