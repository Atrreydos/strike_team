package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.VoteDay;

import java.time.LocalDate;

import static ru.vigovskiy.strike_team.EventVotingTestData.EVENT_VOTING_1;
import static ru.vigovskiy.strike_team.EventVotingTestData.EVENT_VOTING_2;

public class VoteDayTestData {
    public static final int VOTE_DAY1_ID = 1;
    public static final int VOTE_DAY2_ID = 2;
    public static final int VOTE_DAY3_ID = 3;

    public static final VoteDay VOTE_DAY_1 = new VoteDay(VOTE_DAY1_ID, LocalDate.of(2018, 10, 30), EVENT_VOTING_1);
    public static final VoteDay VOTE_DAY_2 = new VoteDay(VOTE_DAY2_ID, LocalDate.of(2018, 10, 31), EVENT_VOTING_2);
    public static final VoteDay VOTE_DAY_3 = new VoteDay(VOTE_DAY3_ID, LocalDate.of(2018, 10, 31), EVENT_VOTING_1);
}
