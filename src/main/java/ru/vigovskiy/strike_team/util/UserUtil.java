package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.UserDto;
import ru.vigovskiy.strike_team.model.User;

public class UserUtil {

    public static User createUserFromDto(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getLogin(), dto.getPassword());
    }

    public static User updateUserFromDto(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserDto createDtoFromUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword());
    }
}
