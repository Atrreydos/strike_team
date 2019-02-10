package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.dto.eventMember.EventMemberDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.model.EventMember;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.repository.EventMemberRepository;
import ru.vigovskiy.strike_team.service.EventMemberService;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.service.UserService;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

import static ru.vigovskiy.strike_team.util.EventMemberUtil.*;
import static ru.vigovskiy.strike_team.web.SecurityUtil.authUserId;

@Service
public class EventMemberServiceImpl implements EventMemberService {
    private static final Logger log = LoggerFactory.getLogger(EventMemberServiceImpl.class);

    private EventMemberRepository repository;
    private UserService userService;
    private EventService eventService;

    @Autowired
    public EventMemberServiceImpl(EventMemberRepository repository, UserService userService, EventService eventService) {
        this.repository = repository;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Override
    public List<EventMemberDto> getForEvent(int eventId) {
        List<EventMember> members = repository.getForEvent(eventId);
        return createDtosFromEventMembers(members);
    }

    @Override
    public EventMemberDto create(EventMemberDto dto) {
        User user = userService.findById(dto.getUserId());
        Event event = eventService.find(dto.getEventId());
        EventMember eventMember = createEventMemberFromDto(dto, user, event);
        return createDtoFromEventMember(repository.save(eventMember));
    }


    @Override
    public EventMemberDto createOrUpdate(EventMemberDto dto) {
        int userId = authUserId();
        EventMember eventMemberForUserByEvent = repository.getForUserByEvent(userId, dto.getEventId());
        if (eventMemberForUserByEvent != null) {
            dto.setId(eventMemberForUserByEvent.getId());
        }
        dto.setUserId(userId);

        return create(dto);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("EventMember with id {} not found for deleting", id);
            throw new NotFoundException("Not found EventMember for deleting with id = " + id);
        }
    }

    @Override
    public EventMember find(int id) {
        EventMember eventMember = repository.get(id);
        if (eventMember == null) {
            log.error("EventMember with id {} not found", id);
            throw new NotFoundException("Not found eventMember with id = " + id);
        }

        return eventMember;
    }
}