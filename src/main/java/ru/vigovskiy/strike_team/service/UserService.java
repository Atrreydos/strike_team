package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    User create(User user);

    void update(User user);

    void delete(int id) throws NotFoundException;
}