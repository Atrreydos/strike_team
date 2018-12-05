package ru.vigovskiy.strike_team.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.CollectionUtils;
import ru.vigovskiy.strike_team.model.Enums.Role;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_LOGIN, query = "SELECT u FROM User u WHERE u.login=:login"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.login"),
})
@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity implements Identifiable<Integer> {

    public static final String DELETE = "User.delete";
    public static final String BY_LOGIN = "User.getByLogin";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Integer id;

    @Column(name = "login", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    @JsonIgnore
    private List<Vote> votes;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = false;


    public User() {
    }

    public User(Integer id, String name, String login, String password, boolean enabled, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        setRoles(roles);
    }

    public User(Integer id, String name, String login, String password, boolean enabled, Role role, Role... roles) {
        this(id, name, login, password, enabled, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String login, String password, Set<Role> roles) {
        this(id, name, login, password, false, roles);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void addVote(Vote vote) {
        if (votes == null) {
            votes = new ArrayList<>();
        }
        vote.setUser(this);
        votes.add(vote);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                '}';
    }
}
