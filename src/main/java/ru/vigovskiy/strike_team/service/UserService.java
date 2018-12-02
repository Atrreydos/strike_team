package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.dto.user.UserDtoMin;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User get(int id) throws NotFoundException;

    User getByLogin(String login) throws NotFoundException;

    List<User> getAll();

    User create(UserDtoMin dto);

    User create(UserDto dto);

    User update(UserDtoMin dto);

    User update(UserDto dto);

    void delete(int id) throws NotFoundException;

    void setEnabled(int id, boolean enabled);
}