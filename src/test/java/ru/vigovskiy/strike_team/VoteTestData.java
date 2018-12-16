package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.UserVoteDayPK;
import ru.vigovskiy.strike_team.model.Vote;

import static ru.vigovskiy.strike_team.UserTestData.*;
import static ru.vigovskiy.strike_team.VoteDayTestData.*;

public class VoteTestData {
    public static final UserVoteDayPK VOTE1_ID = new UserVoteDayPK(USER1_ID, VOTE_DAY1_ID);
    public static final UserVoteDayPK VOTE2_ID = new UserVoteDayPK(ADMIN1_ID, VOTE_DAY2_ID);
    public static final UserVoteDayPK VOTE3_ID = new UserVoteDayPK(ADMIN1_ID, VOTE_DAY1_ID);

    public static final Vote VOTE_1 = new Vote(VOTE1_ID, DecisionType.ACCEPT, USER_1, VOTE_DAY_1);
    public static final Vote VOTE_2 = new Vote(VOTE2_ID, DecisionType.ACCEPT, ADMIN_1, VOTE_DAY_2);
    public static final Vote VOTE_3 = new Vote(VOTE3_ID, DecisionType.REJECT, ADMIN_1, VOTE_DAY_1);

}
