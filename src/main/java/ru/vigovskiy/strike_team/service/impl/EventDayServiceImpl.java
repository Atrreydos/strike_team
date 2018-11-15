package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.model.EventDay;
import ru.vigovskiy.strike_team.repository.EventDayRepository;
import ru.vigovskiy.strike_team.service.EventDayService;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

@Service
public class EventDayServiceImpl implements EventDayService {
    private static final Logger log = LoggerFactory.getLogger(EventDayServiceImpl.class);

    private EventDayRepository repository;

    @Autowired
    public EventDayServiceImpl(EventDayRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventDay get(int id) throws NotFoundException {
        EventDay eventDay = repository.get(id);
        if (eventDay == null) {
            log.error("EventDay with id {} not found", id);
            throw new NotFoundException("Not found eventDay with id = " + id);
        }
        return eventDay;
    }

    @Override
    public EventDay getWithVotes(int id) throws NotFoundException {
        EventDay eventDay = repository.getWithVotes(id);
        if (eventDay == null) {
            log.error("EventDay with id {} not found", id);
            throw new NotFoundException("Not found eventDay with id = " + id);
        }
        return eventDay;
    }

    @Override
    public List<EventDay> getAll() {
        return repository.getAll();
    }

    @Override
    public EventDay create(EventDay eventDay) {
        return repository.save(eventDay);
    }

    @Override
    public void update(EventDay eventDay) {
        repository.save(eventDay);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("EventDay with id {} not found for deleting", id);
            throw new NotFoundException("Not found eventDay for deleting with id = " + id);
        }
    }
}