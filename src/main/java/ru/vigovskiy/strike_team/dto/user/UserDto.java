package ru.vigovskiy.strike_team.dto.user;

import ru.vigovskiy.strike_team.model.Enums.Role;

import java.util.HashSet;
import java.util.Set;

public class UserDto extends UserDtoMin {

    private static final long serialVersionUID = 1L;

    private boolean enabled;
    private boolean admin;
    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(Integer id, String name, String login, String password, Set<Role> roles, boolean enabled, boolean admin) {
        super(id, name, login, password);
        this.roles = roles;
        this.enabled = enabled;
        this.admin = admin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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

        if (enabled != userDto.enabled) return false;
        if (admin != userDto.admin) return false;
        return roles != null ? roles.equals(userDto.roles) : userDto.roles == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (admin ? 1 : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
