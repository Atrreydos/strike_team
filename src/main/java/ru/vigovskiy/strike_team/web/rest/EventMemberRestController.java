package ru.vigovskiy.strike_team.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vigovskiy.strike_team.dto.eventMember.EventMemberDto;
import ru.vigovskiy.strike_team.service.EventMemberService;

import java.util.List;

@RestController
@RequestMapping(EventMemberRestController.REST_URL)
public class EventMemberRestController {
    static final String REST_URL = "/rest/event-members";

    private final EventMemberService service;

    @Autowired
    public EventMemberRestController(EventMemberService service) {
        this.service = service;
    }

    @GetMapping(value = "event/{eventId}")
    public List<EventMemberDto> getForEvent(@PathVariable("eventId") int eventId) {
        return service.getForEvent(eventId);
    }

    @PostMapping
    public EventMemberDto createOrUpdate(@RequestBody EventMemberDto dto) {
        return service.createOrUpdate(dto);
    }

//    @DeleteMapping(value = "/{voteDayId}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable("voteDayId") int voteDayId) {
//        service.deleteForVoteDay(voteDayId);
//    }
}
