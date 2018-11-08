package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.User;

import java.util.Arrays;
import java.util.Collections;

import static ru.vigovskiy.strike_team.VoteTestData.VOTE_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_2;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_3;

public class UserTestData {
    public static final int USER1_ID = 1;
    public static final int ADMIN1_ID = 2;

    public static final User USER_1 = new User(USER1_ID, "User1", "user1_login", "password", Collections.singletonList(VOTE_1));
    public static final User ADMIN_1 = new User(ADMIN1_ID, "Admin1", "admin1_login", "password", Arrays.asList(VOTE_2, VOTE_3));

}
