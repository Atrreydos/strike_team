package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.VoteDay;

import java.time.LocalDate;

public class VoteDayTestData {
    public static final int VOTE_DAY1_ID = 1;
    public static final int VOTE_DAY2_ID = 2;

    public static final VoteDay VOTE_DAY_1 = new VoteDay(VOTE_DAY1_ID, LocalDate.of(2018, 10, 30));
    public static final VoteDay VOTE_DAY_2 = new VoteDay(VOTE_DAY2_ID, LocalDate.of(2018, 10, 31));
}
