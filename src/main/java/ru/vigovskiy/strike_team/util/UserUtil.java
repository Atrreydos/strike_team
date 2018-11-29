package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.UserDto;
import ru.vigovskiy.strike_team.model.User;

public class UserUtil {

    public static User createUserFromDto(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getLogin(), dto.getPassword());
    }
}
