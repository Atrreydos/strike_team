package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

public interface VoteDayService {

    VoteDayDto get(int id) throws NotFoundException;

    VoteDayDto createOrUpdate(VoteDayDto dto);

    void delete(int id) throws NotFoundException;

    VoteDay find(Integer voteDayId);
}