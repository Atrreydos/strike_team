package ru.vigovskiy.strike_team.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.dto.user.UserDtoMin;
import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getRoles(), user.isEnabled(), user.isAdmin());
    }

    public static List<UserDto> createDtosFromUsers(List<User> users) {
        return Optional.ofNullable(users).orElse(Collections.emptyList()).stream()
                .map(UserUtil::createDtoFromUser).collect(Collectors.toList());
    }

    public static User updateUserFromDto(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        return user;
    }

    public static User copy(User original) {
        return new User(original.getId(), original.getName(), original.getLogin(), original.getPassword(), original.isEnabled(), original.getRoles());
    }
}
