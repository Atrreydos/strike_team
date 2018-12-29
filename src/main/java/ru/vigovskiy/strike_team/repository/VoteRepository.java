package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(Integer id);

    Vote get(Integer id);

    Vote getForUserByVoteDay(int userId, int voteDayId);

    List<Vote> getAll();
}
