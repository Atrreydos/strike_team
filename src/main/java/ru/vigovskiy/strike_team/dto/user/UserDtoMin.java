package ru.vigovskiy.strike_team.dto.user;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDtoMin implements Identifiable<Integer> {

    private Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 5, max = 32, message = "login length must be between 5 and 32 characters")
    private String login;

    @NotBlank
    @Size(min = 5, max = 32, message = "password length must be between 5 and 32 characters")
    private String password;

    public UserDtoMin() {
    }

    public UserDtoMin(Integer id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
