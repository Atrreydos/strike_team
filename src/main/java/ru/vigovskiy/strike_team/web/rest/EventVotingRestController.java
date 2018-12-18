package ru.vigovskiy.strike_team.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.service.EventVotingService;

import java.util.List;

@RestController
@RequestMapping(EventVotingRestController.REST_URL)
public class EventVotingRestController {
    static final String REST_URL = "/rest/event-votings";

    private final EventVotingService service;

    @Autowired
    public EventVotingRestController(EventVotingService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public EventVotingDto get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping
    public List<EventVotingDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public EventVotingDto create(@RequestBody EventVotingDto dto) {
        return service.createOrUpdate(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
