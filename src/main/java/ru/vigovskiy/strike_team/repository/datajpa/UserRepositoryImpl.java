package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.repository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Sort SORT_LOGIN_NAME_ASC = new Sort(Sort.Direction.ASC, "login", "name");

    private final CrudUserRepository repository;

    @Autowired
    public UserRepositoryImpl(CrudUserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElse(null);
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
