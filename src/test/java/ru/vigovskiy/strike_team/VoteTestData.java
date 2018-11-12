package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.DecisionType;
import ru.vigovskiy.strike_team.model.Vote;

import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY_1;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY_2;
import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;

public class VoteTestData {
    public static final int VOTE1_ID = 1;
    public static final int VOTE2_ID = 2;
    public static final int VOTE3_ID = 3;

    public static final Vote VOTE_1 = new Vote(VOTE1_ID, DecisionType.ACCEPT, USER_1, EVENT_DAY_1);
    public static final Vote VOTE_2 = new Vote(VOTE2_ID, DecisionType.ACCEPT, ADMIN_1, EVENT_DAY_2);
    public static final Vote VOTE_3 = new Vote(VOTE3_ID, DecisionType.REJECT, ADMIN_1, EVENT_DAY_1);

}
