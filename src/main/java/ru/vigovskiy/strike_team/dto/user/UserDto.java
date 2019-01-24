package ru.vigovskiy.strike_team.dto.user;

import ru.vigovskiy.strike_team.model.Enums.Role;

import java.util.HashSet;
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

    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        if (!super.equals(o)) return false;

        UserDto userDto = (UserDto) o;

        return roles != null ? roles.equals(userDto.roles) : userDto.roles == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
