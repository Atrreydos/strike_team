package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

public interface VoteDayService {

    VoteDay get(int id) throws NotFoundException;
}