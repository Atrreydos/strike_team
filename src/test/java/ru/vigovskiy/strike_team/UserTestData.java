package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.User;

import java.util.Arrays;
import java.util.List;

public class UserTestData {
    public static final int USER1_ID = 1;
    public static final int ADMIN1_ID = 2;

    public static final User USER_1 = new User(USER1_ID, "User1", "user1_login", "password", Role.USER);
    public static final User ADMIN_1 = new User(ADMIN1_ID, "Admin1", "admin1_login", "password", Role.USER, Role.ADMIN);

    public static final List<User> USERS = Arrays.asList(ADMIN_1, USER_1);

}
