package ru.vigovskiy.strike_team.dto.user;

import ru.vigovskiy.strike_team.model.Enums.Role;

import java.util.Set;

public class UserDto extends UserDtoMin {
    private static final long serialVersionUID = 1L;

    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(Integer id, String name, String login, String password, Set<Role> roles) {
        super(id, name, login, password);
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
