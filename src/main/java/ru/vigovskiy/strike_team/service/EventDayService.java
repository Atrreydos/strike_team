package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.model.EventDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface EventDayService {

    EventDay get(int id) throws NotFoundException;

    List<EventDay> getAll();

    EventDay create(EventDay event);

    void update(EventDay event);

    void delete(int id) throws NotFoundException;
}