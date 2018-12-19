package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.repository.EventVotingRepository;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.service.EventVotingService;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

import static ru.vigovskiy.strike_team.util.EventUtil.createEventFromDto;
import static ru.vigovskiy.strike_team.util.EventVotingUtil.*;

@Service
public class EventVotingServiceImpl implements EventVotingService {
    private static final Logger log = LoggerFactory.getLogger(EventVotingServiceImpl.class);

    private EventVotingRepository repository;
    private EventService eventService;

    @Autowired
    public EventVotingServiceImpl(EventVotingRepository repository, EventService eventService) {
        this.repository = repository;
        this.eventService = eventService;
    }

    @Override
    public EventVotingDto get(int id) throws NotFoundException {
        EventVoting eventVoting = repository.get(id);
        if (eventVoting == null) {
            log.error("EventVoting with id {} not found", id);
            throw new NotFoundException("Not found eventVoting with id = " + id);
        }

        return createDtoFromEventVoting(eventVoting);
    }

    @Override
    public EventVotingDtoFull getWithVoteDays(int id) throws NotFoundException {
        EventVoting eventVoting = repository.getWithVoteDays(id);
        if (eventVoting == null) {
            log.error("EventVoting with id {} not found", id);
            throw new NotFoundException("Not found eventVoting with id = " + id);
        }

        return createDtoFullFromEventVoting(eventVoting);
    }

    @Override
    public List<EventVotingDto> getAll() {
        List<EventVoting> eventVotings = repository.getAll();

        return createDtosFromEventVotings(eventVotings);
    }

    @Override
    public EventVotingDto createOrUpdate(EventVotingDto dto) {
        Event event;
        Integer eventId = dto.getEvent().getId();
        if (eventId == null) {
            EventDto eventDto = eventService.create(dto.getEvent());
            dto.setEvent(eventDto);
            event = createEventFromDto(eventDto);
        }
        else {
            event = eventService.find(eventId);
        }

        EventVoting eventVoting = createEventVotingFromDto(dto, event);
        eventVoting = repository.save(eventVoting);
        return createDtoFromEventVoting(eventVoting);
    }


    @Override
    public void delete(int id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("EventVoting with id {} not found for deleting", id);
            throw new NotFoundException("Not found eventVoting for deleting with id = " + id);
        }
    }
}