package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull;
import ru.vigovskiy.strike_team.model.Enums.EventStatus;
import ru.vigovskiy.strike_team.model.Enums.EventVotingStatus;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.repository.EventRepository;
import ru.vigovskiy.strike_team.repository.EventVotingRepository;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.service.EventVotingService;
import ru.vigovskiy.strike_team.util.EventVotingUtil;
import ru.vigovskiy.strike_team.util.VoteDayUtil;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.vigovskiy.strike_team.util.EventUtil.createEventFromDto;
import static ru.vigovskiy.strike_team.util.EventVotingUtil.*;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.getAcceptVotesCount;

@Service
public class EventVotingServiceImpl implements EventVotingService {
    private static final Logger log = LoggerFactory.getLogger(EventVotingServiceImpl.class);

    private EventVotingRepository repository;
    private EventService eventService;
    private EventRepository eventRepository;

    @Autowired
    public EventVotingServiceImpl(EventVotingRepository repository, EventService eventService, EventRepository eventRepository) {
        this.repository = repository;
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventVotingDto get(int id) throws NotFoundException {
        EventVoting eventVoting = find(id);

        return createDtoFromEventVoting(eventVoting);
    }

    @Override
    public EventVotingDtoFull getWithVoteDays(int id) throws NotFoundException {
        EventVoting eventVoting = findWithVoteDays(id);

        return createDtoFullFromEventVoting(eventVoting);
    }

    @Override
    public List<EventVotingDto> getAll() {
        return repository.getAll().stream()
                .map(EventVotingUtil::createDtoFromEventVoting)
                .sorted(Comparator.comparing(EventVotingDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public EventVotingDto createOrUpdate(EventVotingDto dto) {
        EventDto eventFromDto = dto.getEvent();
        eventFromDto.setStatus(EventStatus.IN_VOTING);
        EventDto eventDto = eventService.createOrUpdate(eventFromDto);
        Event event = createEventFromDto(eventDto);

        EventVoting eventVoting = createEventVotingFromDto(dto, event);
        if (eventVoting.getId() == null) {
            eventVoting.setStatus(EventVotingStatus.NEW_VOTING);
        }
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

    @Override
    public List<VoteDay> getMaxAcceptedDays(int eventVotingId) throws NotFoundException {
        EventVoting eventVoting = findWithVoteDays(eventVotingId);
        List<VoteDay> voteDays = eventVoting.getVoteDays();
        Comparator<VoteDay> comparator = Comparator.comparingInt(VoteDayUtil::getAcceptVotesCount);
        int maxAcceptCount = getAcceptVotesCount(voteDays.stream().max(comparator).orElse(null));

        return voteDays.stream().filter(voteDay -> getAcceptVotesCount(voteDay) == (maxAcceptCount)).collect(Collectors.toList());
    }

    @Override
    public void setupDayForEvent(int eventVotingId, int voteDayId) {
        EventVoting eventVoting = findWithVoteDays(eventVotingId);
        Event event = eventVoting.getEvent();
        VoteDay voteDay = eventVoting.getVoteDays().stream().filter(vd -> vd.getId() == voteDayId).findFirst().orElse(null);
        if (voteDay == null) {
            throw new IllegalArgumentException(String.format("EventVoting %d don't have VoteDay %d", eventVotingId, voteDayId));
        }
        eventVoting.setStatus(EventVotingStatus.CLOSED_VOTING);
        repository.save(eventVoting);
        event.setDate(voteDay.getDay());
        event.setStatus(EventStatus.UPCOMING_EVENT);
        eventRepository.save(event);
    }

    @Override
    public EventVoting find(Integer eventVotingId) {
        EventVoting eventVoting = repository.get(eventVotingId);
        if (eventVoting == null) {
            log.error("EventVoting with id {} not found", eventVotingId);
            throw new NotFoundException("Not found eventVoting with id = " + eventVotingId);
        }

        return eventVoting;
    }

    @Override
    public EventVoting findWithVoteDays(Integer eventVotingId) {
        EventVoting eventVoting = repository.getWithVoteDays(eventVotingId);
        if (eventVoting == null) {
            log.error("EventVoting with id {} not found", eventVotingId);
            throw new NotFoundException("Not found eventVoting with id = " + eventVotingId);
        }

        return eventVoting;
    }


}