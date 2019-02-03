package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface EventService {

    EventDto get(int id) throws NotFoundException;

    List<EventDto> getAll();

    List<EventDto> getAllUpcoming();

    List<EventDto> getAllInVoting();

    EventDto createOrUpdate(EventDto dto);

    void delete(int id) throws NotFoundException;

    Event find(int id);
}