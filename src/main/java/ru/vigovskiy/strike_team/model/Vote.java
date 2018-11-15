package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity implements Identifiable<UserEventDayPK> {

    @EmbeddedId
    private UserEventDayPK id;

    @Column(name = "decision")
    @NotNull
    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_day_id", insertable = false, updatable = false)
    @NotNull
    private EventDay eventDay;

    public Vote() {
    }

    public Vote(DecisionType decisionType, User user, EventDay eventDay) {
        this.decisionType = decisionType;
        this.user = user;
        this.eventDay = eventDay;
    }

    public Vote(UserEventDayPK id, DecisionType decisionType, User user, EventDay eventDay) {
        this.id = id;
        this.decisionType = decisionType;
        this.user = user;
        this.eventDay = eventDay;
    }

    @Override
    public UserEventDayPK getId() {
        return id;
    }

    @Override
    public void setId(UserEventDayPK id) {
        this.id = id;
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
        if (decisionType != vote.decisionType) return false;
        if (!user.equals(vote.user)) return false;
        return eventDay.equals(vote.eventDay);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + decisionType.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + eventDay.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", decisionType=" + decisionType +
                ", user=" + user +
                ", eventDay=" + eventDay +
                '}';
    }

}