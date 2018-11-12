package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.UserEventDayPK;
import ru.vigovskiy.strike_team.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(UserEventDayPK id);

    Vote get(UserEventDayPK id);

    List<Vote> getAll();
}
