package ru.vigovskiy.strike_team.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.service.VoteDayService;

import java.util.List;

@RestController
@RequestMapping(VoteDayRestController.REST_URL)
public class VoteDayRestController {
    static final String REST_URL = "/rest/vote-days";

    private final VoteDayService service;

    @Autowired
    public VoteDayRestController(VoteDayService service) {
        this.service = service;
    }

    @GetMapping(value = "/{eventVotingId}")
    public List<VoteDayDto> getForEventVoting(@PathVariable("eventVotingId") int eventVotingId) {
        return service.getForEventVoting(eventVotingId);
    }

    @PostMapping
    public VoteDayDto create(@RequestBody VoteDayDto dto) {
        return service.createOrUpdate(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
