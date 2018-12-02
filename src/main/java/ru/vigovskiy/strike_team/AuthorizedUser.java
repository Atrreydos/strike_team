package ru.vigovskiy.strike_team;

import ru.vigovskiy.strike_team.dto.user.UserDto;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserDto userDto;

    public AuthorizedUser(User user) {
        super(user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userDto = UserUtil.createDtoFromUser(user);
    }

    public int getId() {
        return userDto.getId();
    }

    public void update(UserDto newDto) {
        userDto = newDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    @Override
    public String toString() {
        return userDto.toString();
    }
}
