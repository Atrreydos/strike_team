package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.Enums.MemberDecision;
import ru.vigovskiy.strike_team.model.EventMember;

import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_2;
import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;

public class EventMemberTestData {
    public static final Integer EVENT_MEMBER_1_ID = 1;
    public static final Integer EVENT_MEMBER_2_ID = 2;
    public static final Integer EVENT_MEMBER_3_ID = 3;

    public static final EventMember EVENT_MEMBER_1 = new EventMember(EVENT_MEMBER_1_ID, MemberDecision.ACCEPT, USER_1, EVENT_1);
    public static final EventMember EVENT_MEMBER_2 = new EventMember(EVENT_MEMBER_2_ID, MemberDecision.REJECT, USER_1, EVENT_2);
    public static final EventMember EVENT_MEMBER_3 = new EventMember(EVENT_MEMBER_3_ID, MemberDecision.REJECT, ADMIN_1, EVENT_1);
}
