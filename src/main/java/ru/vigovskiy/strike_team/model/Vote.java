package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity implements Identifiable<UserVoteDayPK> {

    @EmbeddedId
    private UserVoteDayPK id;

    @Column(name = "decision")
    @NotNull
    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @NotNull
    private User user;

    @MapsId("voteDayId")
    @ManyToOne
    @JoinColumn(name = "vote_day_id", insertable = false, updatable = false)
    @NotNull
    private VoteDay voteDay;

    public Vote() {
    }

    public Vote(DecisionType decisionType, User user, VoteDay voteDay) {
        this.decisionType = decisionType;
        this.user = user;
        this.voteDay = voteDay;
    }

    public Vote(UserVoteDayPK id, DecisionType decisionType, User user, VoteDay voteDay) {
        this.id = id;
        this.decisionType = decisionType;
        this.user = user;
        this.voteDay = voteDay;
    }

    @Override
    public UserVoteDayPK getId() {
        return id;
    }

    @Override
    public void setId(UserVoteDayPK id) {
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

    public VoteDay getVoteDay() {
        return voteDay;
    }

    public void setVoteDay(VoteDay voteDay) {
        this.voteDay = voteDay;
    }
}
