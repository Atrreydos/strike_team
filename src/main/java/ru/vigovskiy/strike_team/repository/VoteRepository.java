package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int id);

    Vote get(int id);

    List<Vote> getAll();
}
