package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.AuthorizedUser;
import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.dto.user.UserDtoMin;
import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.repository.UserRepository;
import ru.vigovskiy.strike_team.service.UserService;
import ru.vigovskiy.strike_team.util.UserUtil;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

import static ru.vigovskiy.strike_team.util.UserUtil.*;

/*TODO сделать тип возвращаемы на DTO*/
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto get(int id) throws NotFoundException {
        User user = repository.get(id);
        if (user == null) {
            log.error("User with id {} not found", id);
            throw new NotFoundException("Not found user with id = " + id);
        }
        return createDtoFromUser(user);
    }

    @Override
    public UserDto getByLogin(String login) throws NotFoundException {
        User user = repository.getByLogin(login);
        if (user == null) {
            log.info("User with login {} not found", login);
            throw new NotFoundException("Not found user with login = " + login);
        }
        return createDtoFromUser(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = repository.getAll();
        return createDtosFromUsers(users);
    }

    @Override
    public int getEnabledCount() {
        return repository.getEnabledCount();
    }

    @Transactional
    @Override
    public UserDto create(UserDtoMin dto) {
        if (dto.isNew()) {
            User user = createUserFromDtoMin(dto);
            return createDtoFromUser(repository.save(prepareToSave(user, passwordEncoder)));
        }
        else {
            return update(dto);
        }
    }

    @Transactional
    @Override
    public UserDto create(UserDto dto) {
        if (dto.isNew()) {
            User user = createUserFromDto(dto);
            user = repository.save(prepareToSave(user, passwordEncoder));
            return createDtoFromUser(user);
        }
        else {
            return update(dto);
        }
    }

    @Transactional
    @Override
    public UserDto update(UserDtoMin dto) {
        if (!dto.isNew()) {
            User user = findById(dto.getId());
            UserUtil.updateUserFromDtoMin(user, dto);
            return createDtoFromUser(repository.save(prepareToSave(user, passwordEncoder)));
        }
        else {
            return create(dto);
        }
    }

    @Transactional
    @Override
    public UserDto update(UserDto dto) {
        if (!dto.isNew()) {
            User user = findById(dto.getId());
            UserUtil.updateUserFromDto(user, dto);
            user = repository.save(prepareToSave(user, passwordEncoder));
            return createDtoFromUser(user);
        }
        else {
            return create(dto);
        }
    }

    @Transactional
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
        User user = findById(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @Transactional
    @Override
    public void setAdmin(int id, boolean admin) {
        User user = findById(id);
        if (admin) {
            user.addRole(Role.ROLE_ADMIN);
        }
        else {
            user.getRoles().remove(Role.ROLE_ADMIN);
        }
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Override
    public User findById(int id) throws NotFoundException {
        User user = repository.get(id);
        if (user == null) {
            log.error("User with id {} not found", id);
            throw new NotFoundException("Not found user with id = " + id);
        }
        return user;
    }
}