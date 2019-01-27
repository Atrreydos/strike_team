package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.Enums.EventVotingStatus;
import ru.vigovskiy.strike_team.model.EventVoting;

import java.util.Arrays;
import java.util.List;

import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_2;

public class EventVotingTestData {
    public static final int EVENT_VOTING_1_ID = 1;
    public static final int EVENT_VOTING_2_ID = 2;

    public static final EventVoting EVENT_VOTING_1 = new EventVoting(EVENT_VOTING_1_ID, "Описание 1", EventVotingStatus.NEW_VOTING, EVENT_1);
    public static final EventVoting EVENT_VOTING_2 = new EventVoting(EVENT_VOTING_2_ID, "Описание 2", EventVotingStatus.NEW_VOTING, EVENT_2);

    public static final List<EventVoting> EVENT_VOTINGS = Arrays.asList(EVENT_VOTING_1, EVENT_VOTING_2);
}
