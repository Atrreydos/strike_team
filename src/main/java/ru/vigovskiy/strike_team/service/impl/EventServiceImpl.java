package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.dto.EventDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.repository.EventRepository;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventDto get(int id) throws NotFoundException {
        Event event = repository.get(id);
        if (event == null) {
            log.error("Event with id {} not found", id);
            throw new NotFoundException("Not found event with id = " + id);
        }
        return new EventDto(event);
    }

    @Override
    public Event getWithEventDays(int id) throws NotFoundException {
        Event event = repository.getWithEventDays(id);
        if (event == null) {
            log.error("Event with id {} not found", id);
            throw new NotFoundException("Not found event with id = " + id);
        }
        return event;
    }

    @Override
    public List<EventDto> getAll() {
        return repository.getAll().stream()
                .map(EventDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto create(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        return new EventDto(repository.save(event));
    }

    @Override
    public void update(EventDto eventDto) {
        Event event = repository.get(eventDto.getId());
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
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