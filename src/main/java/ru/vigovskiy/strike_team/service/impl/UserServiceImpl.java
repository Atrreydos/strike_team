package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.dto.user.UserDtoMin;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.repository.UserRepository;
import ru.vigovskiy.strike_team.service.UserService;
import ru.vigovskiy.strike_team.util.UserUtil;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

import static ru.vigovskiy.strike_team.util.UserUtil.createUserFromDto;
import static ru.vigovskiy.strike_team.util.UserUtil.createUserFromDtoMin;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User get(int id) throws NotFoundException {
        User user = repository.get(id);
        if (user == null) {
            log.error("User with id {} not found", id);
            throw new NotFoundException("Not found user with id = " + id);
        }
        return user;
    }

    @Override
    public User getByLogin(String login) throws NotFoundException {
        User user = repository.getByLogin(login);
        if (user == null) {
            log.info("User with login {} not found", login);
            throw new NotFoundException("Not found user with login = " + login);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User create(UserDtoMin dto) {
        if (dto.isNew()) {
            User user = createUserFromDtoMin(dto);
            return repository.save(user);
        }
        else {
            return update(dto);
        }
    }

    @Override
    public User create(UserDto dto) {
        if (dto.isNew()) {
            User user = createUserFromDto(dto);
            return repository.save(user);
        }
        else {
            return update(dto);
        }
    }

    @Transactional
    @Override
    public User update(UserDtoMin dto) {
        if (!dto.isNew()) {
            User user = get(dto.getId());
            return repository.save(UserUtil.updateUserFromDtoMin(user, dto));
        }
        else {
            return create(dto);
        }
    }

    @Transactional
    @Override
    public User update(UserDto dto) {
        if (!dto.isNew()) {
            User user = get(dto.getId());
            return repository.save(UserUtil.updateUserFromDto(user, dto));
        }
        else {
            return create(dto);
        }
    }

    @Override
    public void delete(int id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("User with id {} not found for deleting", id);
            throw new NotFoundException("Not found user for deleting with id = " + id);
        }
    }

    @Transactional
    @Override
    public void setEnabled(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }
}