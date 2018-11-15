package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.Event;

import java.util.List;

public interface EventRepository {

    Event save(Event event);

    boolean delete(int id);

    Event get(int id);

    Event getWithEventDays(int id);

    List<Event> getAll();
}
