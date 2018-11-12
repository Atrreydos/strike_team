package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.User;

public class UserTestData {
    public static final int USER1_ID = 1;
    public static final int ADMIN1_ID = 2;

    public static final User USER_1 = new User(USER1_ID, "User1", "user1_login", "password");
    public static final User ADMIN_1 = new User(ADMIN1_ID, "Admin1", "admin1_login", "password");

}
