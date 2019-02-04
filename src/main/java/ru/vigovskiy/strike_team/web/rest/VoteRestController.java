package ru.vigovskiy.strike_team.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.service.VoteService;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    static final String REST_URL = "/rest/votes";

    private final VoteService service;

    @Autowired
    public VoteRestController(VoteService service) {
        this.service = service;
    }

    @PostMapping
    public VoteDto createOrUpdate(@RequestBody VoteDto dto) {
        return service.createOrUpdate(dto);
    }

    @DeleteMapping(value = "/{voteDayId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("voteDayId") int voteDayId) {
        service.deleteForVoteDay(voteDayId);
    }
}
