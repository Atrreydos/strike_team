package ru.vigovskiy.strike_team.dto;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class UserDto implements Identifiable<Integer> {

    private Integer id;

    private String name;

    private String login;

    private String password;

    public UserDto() {
    }

    public UserDto(Integer id, String name, String login, String password) {
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
