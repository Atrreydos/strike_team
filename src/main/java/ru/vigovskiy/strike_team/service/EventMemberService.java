package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.eventMember.EventMemberDto;
import ru.vigovskiy.strike_team.model.EventMember;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface EventMemberService {

    List<EventMemberDto> getForEvent(int eventId);

    EventMemberDto create(EventMemberDto dto);

    EventMemberDto createOrUpdate(EventMemberDto dto);

    void delete(int id) throws NotFoundException;

    EventMember find(int id);
}