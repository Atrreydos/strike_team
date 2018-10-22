package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
