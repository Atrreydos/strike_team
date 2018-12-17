package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.EventVoting;

import java.util.List;

public interface EventVotingRepository {

    EventVoting save(EventVoting eventVoting);

    boolean delete(int id);

    EventVoting get(int id);

    EventVoting getWithVoteDays(int id);

    List<EventVoting> getAll();
}
