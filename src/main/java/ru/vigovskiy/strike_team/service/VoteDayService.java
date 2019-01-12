package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDtoFull;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface VoteDayService {

    VoteDayDto get(int id) throws NotFoundException;

    List<VoteDayDtoFull> getForEventVoting(int eventVotingId);

    VoteDayDto createOrUpdate(VoteDayDto dto);

    void delete(int id) throws NotFoundException;

    VoteDay find(Integer voteDayId);
}