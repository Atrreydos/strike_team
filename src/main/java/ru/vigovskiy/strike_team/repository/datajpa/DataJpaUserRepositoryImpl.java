package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_LOGIN_NAME_ASC = new Sort(Sort.Direction.ASC, "login", "name");

    @Autowired
    private CrudUserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findByIdWithVotes(id);
    }

    @Override
    public User getByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_LOGIN_NAME_ASC);
    }
}
