package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.dto.user.UserDtoMin;
import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.User;

import java.util.Collections;

public class UserUtil {

    public static User createUserFromDtoMin(UserDtoMin dto) {
        return new User(dto.getId(), dto.getName(), dto.getLogin(), dto.getPassword(), Collections.singleton(Role.ROLE_USER));
    }

    public static UserDtoMin createDtoMinFromUser(User user) {
        return new UserDtoMin(user.getId(), user.getName(), user.getLogin(), user.getPassword());
    }

    public static User updateUserFromDtoMin(User user, UserDtoMin dto) {
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static User createUserFromDto(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getLogin(), dto.getPassword(), dto.getRoles());
    }

    public static UserDto createDtoFromUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getRoles());
    }

    public static User updateUserFromDto(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles());
        return user;
    }
}
