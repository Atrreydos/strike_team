package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.EventDay;

import java.util.List;

public interface EventDayRepository {

    EventDay save(EventDay event);

    boolean delete(int id);

    EventDay get(int id);

    EventDay getWithVotes(int id);

    List<EventDay> getAll();
}
