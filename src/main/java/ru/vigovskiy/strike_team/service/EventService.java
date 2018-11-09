package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface EventService {

    Event get(int id) throws NotFoundException;

    List<Event> getAll();

    Event create(Event event);

    void update(Event event);

    void delete(int id) throws NotFoundException;
}