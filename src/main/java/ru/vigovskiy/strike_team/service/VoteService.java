package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface VoteService {

    Vote get(int id) throws NotFoundException;

    List<Vote> getAll();

    Vote create(Vote vote);

    void update(Vote vote);

    void delete(int id) throws NotFoundException;
}