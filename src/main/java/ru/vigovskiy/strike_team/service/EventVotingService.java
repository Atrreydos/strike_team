package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface EventVotingService {

    EventVotingDto get(int id) throws NotFoundException;

    EventVotingDtoFull getWithVoteDays(int id) throws NotFoundException;

    List<EventVotingDto> getAll();

    EventVotingDto createOrUpdate(EventVotingDto event);

    void delete(int id) throws NotFoundException;

    EventVoting find(Integer eventVotingId);

    List<VoteDay> getMaxAcceptedDays(int eventVotingId) throws NotFoundException;

    EventVoting findWithVoteDays(Integer eventVotingId);

    void setupDayForEvent(int eventVotingId, int voteDayId);
}