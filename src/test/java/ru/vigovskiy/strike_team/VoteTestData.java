package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.DecisionType;
import ru.vigovskiy.strike_team.model.UserEventDayPK;
import ru.vigovskiy.strike_team.model.Vote;

import static ru.vigovskiy.strike_team.EventDayTestData.*;
import static ru.vigovskiy.strike_team.UserTestData.*;

public class VoteTestData {
    public static final UserEventDayPK VOTE1_ID = new UserEventDayPK(USER1_ID, EVENT_DAY1_ID);
    public static final UserEventDayPK VOTE2_ID = new UserEventDayPK(ADMIN1_ID, EVENT_DAY2_ID);
    public static final UserEventDayPK VOTE3_ID = new UserEventDayPK(ADMIN1_ID, EVENT_DAY1_ID);

    public static final Vote VOTE_1 = new Vote(VOTE1_ID, DecisionType.ACCEPT, USER_1, EVENT_DAY_1);
    public static final Vote VOTE_2 = new Vote(VOTE2_ID, DecisionType.ACCEPT, ADMIN_1, EVENT_DAY_2);
    public static final Vote VOTE_3 = new Vote(VOTE3_ID, DecisionType.REJECT, ADMIN_1, EVENT_DAY_1);

}
