package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.UserVoteDayPK;
import ru.vigovskiy.strike_team.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(UserVoteDayPK id);

    Vote get(UserVoteDayPK id);

    List<Vote> getAll();
}
