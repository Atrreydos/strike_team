package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.repository.EventRepository;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.util.EventUtil;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.vigovskiy.strike_team.util.EventUtil.createDtoFromEvent;
import static ru.vigovskiy.strike_team.util.EventUtil.createEventFromDto;

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
        return createDtoFromEvent(event);
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
                .map(EventUtil::createDtoFromEvent)
                .collect(Collectors.toList());
    }

    @Override
    public Event create(EventDto dto) {
        if (dto.isNew()) {
            Event event = createEventFromDto(dto);
            return repository.save(event);
        }
        else {
            return update(dto);
        }
    }

    @Override
    public Event update(EventDto dto) {
        if (!dto.isNew()) {
            Event event = repository.get(dto.getId());
            return repository.save(EventUtil.updateEventFromDto(event, dto));
        }
        else {
            return create(dto);
        }
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