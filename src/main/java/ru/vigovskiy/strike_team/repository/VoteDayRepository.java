package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.VoteDay;

import java.util.List;

public interface VoteDayRepository {

    VoteDay save(VoteDay voteDay);

    boolean delete(Integer id);

    VoteDay get(Integer id);

    List<VoteDay> getAll();

    List<VoteDay> getForEventVoting(int eventVotingId);
}
