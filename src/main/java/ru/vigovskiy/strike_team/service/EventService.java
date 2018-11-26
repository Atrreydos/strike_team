package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.EventDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface EventService {

    EventDto get(int id) throws NotFoundException;

    Event getWithEventDays(int id) throws NotFoundException;

    List<EventDto> getAll();

    EventDto create(EventDto event);

    void update(EventDto eventDto);

    void delete(int id) throws NotFoundException;
}