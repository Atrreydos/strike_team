package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "vote_seq", sequenceName = "vote_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
    private Integer id;

    @Column(name = "day", nullable = false)
    @NotNull
    private LocalDate day;

    @Column(name = "decision")
    @NotNull
    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Vote() {
    }

    public Vote(Integer id, LocalDate day, DecisionType decisionType, User user) {
        this.id = id;
        this.day = day;
        this.decisionType = decisionType;
        this.user = user;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(DecisionType decisionType) {
        this.decisionType = decisionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", day=" + day +
                ", decisionType=" + decisionType +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;

        Vote vote = (Vote) o;

        return id.equals(vote.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
