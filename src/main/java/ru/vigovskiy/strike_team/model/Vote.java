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

    @ManyToOne
    @JoinColumn(name = "event_day_id", nullable = false)
    @NotNull
    private EventDay eventDay;

    public Vote() {
    }

    public Vote(Integer id, LocalDate day, DecisionType decisionType, User user, EventDay eventDay) {
        this.id = id;
        this.day = day;
        this.decisionType = decisionType;
        this.user = user;
        this.eventDay = eventDay;
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

    public EventDay getEventDay() {
        return eventDay;
    }

    public void setEventDay(EventDay eventDay) {
        this.eventDay = eventDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;

        Vote vote = (Vote) o;

        if (!id.equals(vote.id)) return false;
        if (day != null ? !day.equals(vote.day) : vote.day != null) return false;
        if (decisionType != vote.decisionType) return false;
        if (!user.equals(vote.user)) return false;
        return eventDay.equals(vote.eventDay);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + decisionType.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + eventDay.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", day=" + day +
                ", decisionType=" + decisionType +
                ", user=" + user +
                ", eventDay=" + eventDay +
                '}';
    }

}
