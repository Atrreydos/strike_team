package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Vote;

import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_2;

public class VoteTestData {
    public static final Integer VOTE_1_ID = 1;
    public static final Integer VOTE_2_ID = 2;
    public static final Integer VOTE_3_ID = 3;

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, DecisionType.ACCEPT, USER_1, VOTE_DAY_1);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, DecisionType.ACCEPT, ADMIN_1, VOTE_DAY_2);
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, DecisionType.REJECT, ADMIN_1, VOTE_DAY_1);

}
