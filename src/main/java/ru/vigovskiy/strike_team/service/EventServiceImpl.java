package ru.vigovskiy.strike_team.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.repository.EventRepository;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event get(int id) throws NotFoundException {
        Event event = repository.get(id);
        if (event == null) {
            log.error("Event with id {} not found", id);
            throw new NotFoundException("Not found event with id = " + id);
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        return repository.getAll();
    }

    @Override
    public Event create(Event event) {
        return repository.save(event);
    }

    @Override
    public void update(Event event) {
        repository.save(event);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("Vote with id {} not found for deleting", id);
            throw new NotFoundException("Not found event for deleting with id = " + id);
        }
    }
}